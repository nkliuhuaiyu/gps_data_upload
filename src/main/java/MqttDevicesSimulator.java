import Utility.OnenetDeviceUtility;
import Utility.RestfulUtility;
import config.UploadConfig;
import mqtt.MqttDevice;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import pojo.DeviceInfo;
import pojo.DeviceListResponseDTO;
import pojo.TradeDeviceDO;
import service.TradeDeviceService;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import Constant.Constant;

public class MqttDevicesSimulator {

    public static final Logger logger = Logger.getLogger(MqttDevicesSimulator.class);

    private static final String getDevicesUrl = UploadConfig.GET_DEVICES_URL;


    private static final String productionId = UploadConfig.PRODUCTION_ID;
    private static final String apiKey = UploadConfig.API_KEY;


    public static void main(String[] args){
        logger.trace("Get Data from onenet platform ----------------------------------------------");
        DeviceListResponseDTO deviceListResponseDTO = RestfulUtility.getDeviceListFromUrl(getDevicesUrl , apiKey);
        if(deviceListResponseDTO == null){
            logger.info("Restful service get request return null. URL: " + getDevicesUrl);
            return;
        }
        int devicesCount = deviceListResponseDTO.getData().getTotal_count();
        int perPage = deviceListResponseDTO.getData().getPer_page();
        DeviceInfo[] devices = new DeviceInfo[devicesCount];
        DeviceInfo[] firstPageDevices = deviceListResponseDTO.getData().getDevices();
        for(int i = 0 ; i < firstPageDevices.length ; i ++){
            devices[i] = firstPageDevices[i];
        }
        int curPage = 2;
        int pageCount = devicesCount / perPage + ((devicesCount%perPage) == 0 ? 0 : 1);//page start from 1
        while(curPage <= pageCount){
            DeviceListResponseDTO deviceListPerPage = RestfulUtility.getDeviceListFromUrl(getDevicesUrl + "?page=" + String.valueOf(curPage) , apiKey);
            if(deviceListPerPage == null){
                logger.info("Restful service get requst with page parameter return null. URL: " + getDevicesUrl + "?page=" + String.valueOf(curPage));
                return;
            }
            DeviceInfo[] currentPageDevices = deviceListPerPage.getData().getDevices();
            for(int i = 0 ; i < currentPageDevices.length ; i ++){
                devices[(curPage-1)* perPage + i] = currentPageDevices[i];
            }
            curPage ++;
        }

        logger.trace("Begin to manipulate the datas ----------------------------------------------");
        List<TradeDeviceDO> tradeDeviceDOS = TradeDeviceService.listAllDevices();
        ExecutorService pool = new ThreadPoolExecutor(Constant.CORE_POOL_SIZE , Constant.MAXIMUM_POOL_SIZE , Constant.KEEP_ALIVE_TIME , TimeUnit.SECONDS ,
                new LinkedBlockingDeque<Runnable>());
        for(TradeDeviceDO tradeDeviceDO : tradeDeviceDOS){
            String deviceTitle = OnenetDeviceUtility.generateId(tradeDeviceDO);
            DeviceInfo deviceInfo = OnenetDeviceUtility.findDeviceByTitle(devices , deviceTitle);
            MqttConnectOptions deviceOption = new MqttConnectOptions();
            deviceOption.setUserName(productionId);
            deviceOption.setPassword(apiKey.toCharArray());
            deviceOption.setKeepAliveInterval(300);
            if(deviceInfo != null){
                MqttDevice mqttDevice = new MqttDevice(tradeDeviceDO.getDeviceNo() , deviceOption , deviceInfo);
                pool.submit(mqttDevice);
            }else{
                logger.warn("Can not get device info from onenet by device title " + deviceTitle);
            }
        }
    }
}
