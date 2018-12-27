package Utility;

import Constant.Constant;
import pojo.DeviceInfo;
import org.apache.log4j.Logger;



public class OnenetDeviceUtility {
    private static final Logger logger = Logger.getLogger(OnenetDeviceUtility.class);

    public static DeviceInfo findDeviceByTitle(DeviceInfo[] devices , String deviceTitle){
        for(DeviceInfo deviceInfo : devices){
            if(deviceInfo.getTitle().equals(deviceTitle)){
                return deviceInfo;
            }
        }
        return null;
    }
}
