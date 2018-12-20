package Constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    /*
     * Mqtt system topic
     */
    public static final String MQTT_SYSTEM_TOPIC = "$dp";

    /*
     * Thread pool configuration
     */
    public static final int CORE_POOL_SIZE = 600;
    public static final int MAXIMUM_POOL_SIZE = 600;
    public static final long KEEP_ALIVE_TIME = 0L;

    /*
     * Device type
     */

    public static final int DEVICE_TYPE_BOM_IN_DB = 2;

    public static final int DEVICE_TYPE_POS_IN_DB = 1;

    /*
     * Onenet name convention , eg. DDZF.BOM.0105
     */
    public static final String DEVICE_TYPE_BOM = "BOM";
    public static final String DEVICE_TYPE_POS = "POS";
    public static final String SPLITER = ".";
    public static final int DEVICE_NUMBER_SUFFIX_LENGHT = 4;

    public static final Map<String , String> DEVICE_PREFIX_MAP = new HashMap<String , String>();
    static{
        DEVICE_PREFIX_MAP.put("20000004","SBZF");
        DEVICE_PREFIX_MAP.put("20000003","DDZF");
        DEVICE_PREFIX_MAP.put("10000001","SYYD");
    }
}
