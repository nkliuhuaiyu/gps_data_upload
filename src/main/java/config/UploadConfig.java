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

    public static Date UPLOADING_START_TIME;
    public static Integer UPLOADING_MAX_TIMES;

    public static Integer UPLOADING_TIME_INTERVAL;

    public static String GET_DEVICES_URL;
    public static String PRODUCTION_ID;
    public static String API_KEY;
    public static String SERVER_IP;
    public static Integer SERVER_PORT;

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
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            UPLOADING_START_TIME = simpleDateFormat.parse( prop.getProperty("time.start") );
            UPLOADING_MAX_TIMES = Integer.valueOf( prop.getProperty("upload.maxmum") );
            UPLOADING_TIME_INTERVAL = Integer.valueOf( prop.getProperty("upload.time.interval") );
            GET_DEVICES_URL = prop.getProperty("getdevices.url");
            PRODUCTION_ID = prop.getProperty("production.id");
            API_KEY = prop.getProperty("apikey");
            SERVER_IP = prop.getProperty("server.ip");
            SERVER_PORT = Integer.valueOf( prop.getProperty("server.port"));

        } catch (ParseException e) {
            logger.error("Time format error : " + e.getMessage() , e);
        }
    }

}
