package Utility;

import Constant.Constant;
import config.UploadConfig;
import pojo.DeviceInfo;
import pojo.DeviceListInfo;
import pojo.TradeDeviceDO;
import org.apache.log4j.Logger;



public class OnenetDeviceUtility {
    private static final Logger logger = Logger.getLogger(OnenetDeviceUtility.class);

    public static String generateId(TradeDeviceDO tradeDeviceDO){
        String onenetDevicePrefix = Constant.DEVICE_PREFIX_MAP.get(tradeDeviceDO.getOrgCode());
        if(onenetDevicePrefix == null){
            return "";
        }
        if(tradeDeviceDO.getDeviceType().equals(Constant.DEVICE_TYPE_POS_IN_DB)){
            return onenetDevicePrefix + Constant.SPLITER + Constant.DEVICE_TYPE_POS + Constant.SPLITER +
                    tradeDeviceDO.getDeviceNo().substring(tradeDeviceDO.getDeviceNo().length() - Constant.DEVICE_NUMBER_SUFFIX_LENGHT);
        }else if(tradeDeviceDO.getDeviceType().equals(Constant.DEVICE_TYPE_BOM_IN_DB)){
            return onenetDevicePrefix + Constant.SPLITER + Constant.DEVICE_TYPE_BOM + Constant.SPLITER +
                    tradeDeviceDO.getDeviceNo().substring(tradeDeviceDO.getDeviceNo().length() - Constant.DEVICE_NUMBER_SUFFIX_LENGHT);
        }else{
            logger.warn("Device type out of the range.");
            return "";
        }
    }

    public static DeviceInfo findDeviceByTitle(DeviceInfo[] devices , String deviceTitle){
        for(DeviceInfo deviceInfo : devices){
            if(deviceInfo.getTitle().equals(deviceTitle)){
                return deviceInfo;
            }
        }
        return null;
    }
}
