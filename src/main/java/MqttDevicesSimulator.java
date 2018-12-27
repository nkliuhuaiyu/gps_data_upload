import Constant.Constant;
import Utility.JedisUtility;
import Utility.OnenetDeviceUtility;
import Utility.RestfulUtility;
import com.alibaba.fastjson.JSON;
import config.UploadConfig;
import mqtt.MqttDevice;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import pojo.BusDeviceDataDO;
import pojo.DeviceInfo;
import pojo.DeviceListResponseDTO;
import redis.clients.jedis.Jedis;

import java.util.Map;


public class MqttDevicesSimulator {

    public static final Logger logger = Logger.getLogger(MqttDevicesSimulator.class);

    private static final String getDevicesUrl = UploadConfig.GET_DEVICES_URL;


    private static final String productionId = UploadConfig.PRODUCTION_ID;
    private static final String apiKey = UploadConfig.API_KEY;


    public static void main(String[] args) throws InterruptedException {
        logger.info("Get Data from onenet platform ----------------------------------------------");
        DeviceListResponseDTO deviceListResponseDTO = RestfulUtility.getDeviceListFromUrl(getDevicesUrl , apiKey);
        if(deviceListResponseDTO == null){
            logger.warn("Restful service get request return null. URL: " + getDevicesUrl);
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
                logger.warn("Restful service get requst with page parameter return null. URL: " + getDevicesUrl + "?page=" + String.valueOf(curPage));
                return;
            }
            DeviceInfo[] currentPageDevices = deviceListPerPage.getData().getDevices();
            for(int i = 0 ; i < currentPageDevices.length ; i ++){
                devices[(curPage-1)* perPage + i] = currentPageDevices[i];
            }
            curPage ++;
        }

        logger.info("Begin to obtain data from redis ----------------------------------------------");

        while(true) {
            Jedis jedis = JedisUtility.getInstance().getJedis(UploadConfig.REDIS_INDEX_BUSDEVICEINFO);
            if(jedis == null){
                logger.warn("JedisUtility returns null!");
                return;
            }
            Map<String, String> busDeviceDataMap = jedis.hgetAll(Constant.BUS_DEVICE_DATA_KEY_IN_REDIS);
            jedis.close();
            if(busDeviceDataMap == null){
                logger.warn("Jedis get hash with key " + Constant.BUS_DEVICE_DATA_KEY_IN_REDIS + " return null");
                return;
            }
            for (Map.Entry<String, String> curBusDeviceData : busDeviceDataMap.entrySet()) {
                String key = curBusDeviceData.getKey();
                String value = curBusDeviceData.getValue();
                BusDeviceDataDO busDeviceDataDO = JSON.parseObject(value, BusDeviceDataDO.class);
                DeviceInfo deviceInfo = OnenetDeviceUtility.findDeviceByTitle(devices, busDeviceDataDO.getPosid());
                MqttConnectOptions deviceOption = new MqttConnectOptions();
                deviceOption.setUserName(productionId);
                deviceOption.setPassword(apiKey.toCharArray());
                deviceOption.setKeepAliveInterval(300);
                if (deviceInfo != null) {
                    MqttDevice mqttDevice = new MqttDevice(deviceOption, deviceInfo.getId(), busDeviceDataDO);
                    mqttDevice.handleMqtt();
                } else {
                    logger.warn("Can not get device info from onenet by bus pos id" + busDeviceDataDO.getPosid());
                }
            }
            Thread.sleep(UploadConfig.UPLOAD_INTERVAL);
        }
    }
}
