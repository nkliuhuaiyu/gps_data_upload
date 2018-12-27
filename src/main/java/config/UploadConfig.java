package config;

import org.apache.log4j.Logger;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Date;

public class UploadConfig {

    private static Logger logger = Logger.getLogger(UploadConfig.class);

    public static String GET_DEVICES_URL;
    public static String PRODUCTION_ID;
    public static String API_KEY;
    public static String SERVER_IP;
    public static int SERVER_PORT;
    public static int UPLOAD_INTERVAL;

    //public static boolean REDIS_ENABLE;
    //public static boolean REDIS_AUTH;
    public static String REDIS_IP;
    public static int REDIS_PORT;
    public static String REDIS_PWD;
    public static int REDIS_TIMEOUT;
    public static int REDIS_POOL_MAXTOTAL;
    public static int REDIS_POOL_MAXIDLE;
    public static int REDIS_POOL_MAXWAITMILIS;
    public static boolean REDIS_POOL_TESTONBORROW;
    public static boolean REDIS_POOL_TESTONRETURN;
    public static int REDIS_POOL_RETRYNUM;
    public static int REDIS_INDEX_BUSDEVICEINFO;
    public static int REDIS_INDEX_DEFAULT;

    static {
       Properties prop = new Properties();

        InputStream is = null;
        try {
            is = new FileInputStream(new File(System.getProperty("user.dir") + "/upload.properties"));
            prop.load(is);

        } catch (FileNotFoundException e) {
            logger.error("Config file upload.properties read fail : " + e.getMessage() , e);
        } catch (IOException e) {
            logger.error("Properties load fail( prop.load ) : " + e.getMessage() , e);
        }

         GET_DEVICES_URL = prop.getProperty("getdevices.url");
         PRODUCTION_ID = prop.getProperty("production.id");
         API_KEY = prop.getProperty("apikey");
         SERVER_IP = prop.getProperty("server.ip");
         SERVER_PORT = Integer.valueOf( prop.getProperty("server.port"));
        UPLOAD_INTERVAL = Integer.valueOf( prop.getProperty("upload.interval"));

         //REDIS_ENABLE = Boolean.valueOf( prop.getProperty("redis.enable"));
         //REDIS_AUTH = Boolean.valueOf( prop.getProperty("redis.auth"));
         REDIS_IP = prop.getProperty("redis.ip");
         REDIS_PORT = Integer.valueOf( prop.getProperty("redis.port"));
         REDIS_PWD = prop.getProperty("redis.pwd");
         REDIS_TIMEOUT = Integer.valueOf( prop.getProperty("redis.timeout"));
         REDIS_POOL_MAXTOTAL = Integer.valueOf( prop.getProperty("redis.pool.maxTotal"));
         REDIS_POOL_MAXIDLE = Integer.valueOf( prop.getProperty("redis.pool.maxIdle"));
         REDIS_POOL_MAXWAITMILIS = Integer.valueOf( prop.getProperty("redis.pool.maxWaitMilis"));
         REDIS_POOL_TESTONBORROW = Boolean.valueOf( prop.getProperty("redis.pool.testOnBorrow"));
         REDIS_POOL_TESTONRETURN = Boolean.valueOf( prop.getProperty("redis.pool.testOnReturn"));
         REDIS_POOL_RETRYNUM = Integer.valueOf( prop.getProperty("redis.pool.retryNum"));
         REDIS_INDEX_BUSDEVICEINFO = Integer.valueOf( prop.getProperty("redis.index.busDeviceInfo"));
         REDIS_INDEX_DEFAULT = Integer.valueOf( prop.getProperty("redis.index.default"));

    }

}
