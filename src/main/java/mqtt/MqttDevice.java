package mqtt;

import Constant.Constant;
import com.alibaba.fastjson.JSON;

import config.UploadConfig;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import pojo.DeviceInfo;
import pojo.TradeDataDO;
import pojo.TradeDataUploadDTO;
import service.TradeDataService;

import javax.naming.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * MqttClient class
 *
 * @author Carson
 * @date 11/27/2017
 * refactor 10/12/2018
 */
public class MqttDevice implements Runnable {

    /**
     *  the IP address if put this program on the proxy server
     */
//	private String serverIP = "172.16.12.21";
//	private int serverPort = 16002;

    private String serverIP = UploadConfig.SERVER_IP;
    private int serverPort = UploadConfig.SERVER_PORT;

    /**
     * the registration information of device on the OneNet platform, including deviceId
     */
    private String deviceId;

    /*
     * the IP address of the OneNet platform in official dev document , it is in liaoning province
     */
    /*
    private String serverIP = "183.230.40.39";
    private int serverPort = 6002;
    */

    /*
     * use to query device datas by TradeDataService
     */
    private String deviceNumber;


    /**
     * MQTT client service
     */
    private MqttClient mqttClient;
    private MqttConnectOptions option;
    private MqttCallback clientCallback;
    private static Logger logger = Logger.getLogger(MqttDevice.class);
    /**
     * message receiver
     */
    private ArrayList<MsgHandler> listenerList = new ArrayList<MsgHandler>();
    /**
     * message context
     */
    private Context mContext;

    /*
     * device properties
     */
    private DeviceInfo deviceInfo;

    public MqttDevice(String deviceNumber, MqttConnectOptions option, DeviceInfo deviceInfo) {
        this.option = option;
        this.deviceId = deviceInfo.getId();
        this.deviceInfo = deviceInfo;
        this.deviceNumber = deviceNumber;
    }

    /**
     * initialize the mqtt client
     */
    private void initMqtt(Context context) {

        mContext = context;
        connect();
    }

    /**
     * reconnect the onenet platform
     */
    private void reConnect() {
        connect();

    }

    /**
     * push the message
     */
    public boolean publish(String topic, String payload) {
        MqttMessage msg = new MqttMessage();
        byte[] msgArr = new byte[payload.length() + 3];
        System.arraycopy(payload.getBytes() , 0 , msgArr , 3 , payload.length());
        msgArr[0] = 0x03;
        msgArr[1] = (byte) (payload.length() >> 8);
        msgArr[2] = (byte) payload.length();
        msg.setPayload(msgArr);
        if (mqttClient != null) {
            if (mqttClient.isConnected()) {
                try {
                    mqttClient.publish(topic, msg);
                    return true;
                } catch (MqttException e) {
                    logger.error("mqtt, 发布失败" + e);
                    return false;
                }
            } else {
                logger.error("mqtt, 网络未连接 - 尝试重新连接");
                reConnect();
                return false;
            }
        } else {
            logger.error("mqtt, 客户端初始化失败");
            return false;
        }
    }

    /**
     * subscribe the topic
     */
    public void subscribe(String topic) {
        try {
            mqttClient.subscribe(topic, 0);
        } catch (MqttException e) {
            logger.error("mqtt, 订阅错误:" + e);
        }
    }

    public boolean isConnected() {
        return mqttClient.isConnected();
    }

    public String getDeviceId() {
        return deviceId;
    }


    private void connect() {
        if (mqttClient == null) {
            // set the callback
            clientCallback = new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    //断开连接
                    logger.info("mqtt, 连接断开!");
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    //接收到消息
                    logger.debug("mqtt, 接收到信息:" + topic);
                    DispachMessage(topic, message);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    //publish成功后调用
                    logger.debug("mqtt,发送成功");
                }
            };
            try {
                mqttClient = new MqttClient(("tcp://" + serverIP + ":" + serverPort), deviceId);
                mqttClient.setCallback(clientCallback);
            } catch (Exception e) {
                logger.error("mqtt,启动服务错误:" + e);
            }
        }
        if (!mqttClient.isConnected()) {
            try {
                /**
                 * Number of retries
                 */
                int count = 0;
                while (count < 5 && !mqttClient.isConnected()) {
                    mqttClient.connect(option);
                    Thread.sleep(5000);
                    count++;
                }
                //连接成功
                if (mqttClient.isConnected()) {
                    logger.info("mqtt, 连接成功");
                } else {
                    logger.error("mqtt, 连接网络错误");
                }
            } catch (Exception e) {
                logger.error("mqtt, 连接错误:" + e);
            }
        }
    }


    public void addListener(MsgHandler msgHandler) {
        if (!listenerList.contains(msgHandler)) {
            listenerList.add(msgHandler);
        }
    }

    public void removeListener(MsgHandler msgHandler) {
        listenerList.remove(msgHandler);
    }

    public void removeAll() {
        listenerList.clear();
    }

    public void DispachMessage(String type, Object data) {
        if (listenerList.isEmpty()) {
            logger.debug("mqtt, 没有消息接收者:" + type);
            return;
        }
        logger.debug("mqtt,发送消息:" + type);
        for (MsgHandler msgHandler : listenerList) {
            msgHandler.onMessage(type, data);
        }
    }

    private void handleMqtt() {

        initMqtt(mContext);
        Date startTimeToUpload = UploadConfig.UPLOADING_START_TIME;

        while(true){
            List<TradeDataDO> tradeDataDOS = TradeDataService.listDataByDeviceId(this.deviceNumber , startTimeToUpload , UploadConfig.UPLOADING_MAX_TIMES);
            if(tradeDataDOS.size() > 0) {
                //mqtt may be disconnected because the long time sleep
                if(!isConnected()){
                    reConnect();
                }
                //listDataByDeviceId sorted by create time in desc
                startTimeToUpload = tradeDataDOS.get(tradeDataDOS.size() - 1).getCreateTime();

                for(TradeDataDO tradeDataDO : tradeDataDOS) {

                    TradeDataUploadDTO tradeDataUploadDTO = new TradeDataUploadDTO();
                    tradeDataUploadDTO.setCardNo(tradeDataDO.getCardNo());
                    tradeDataUploadDTO.setCreateTime(tradeDataDO.getCreateTime());
                    tradeDataUploadDTO.setOrgCode(tradeDataDO.getOrgCode());
                    tradeDataUploadDTO.setPosId(tradeDataDO.getPosId());
                    tradeDataUploadDTO.setTradeMoney(tradeDataDO.getTradeMoney());
                    tradeDataUploadDTO.setTradeTime(tradeDataDO.getTradeTime());

                    boolean success = publish(Constant.MQTT_SYSTEM_TOPIC, JSON.toJSONString(tradeDataUploadDTO));
                    if (success) {
                        logger.debug(String.format("Thread %d publish data success, value %s", Thread.currentThread().getId(), JSON.toJSONString(tradeDataUploadDTO)));
                    }else{
                        logger.error(String.format("Thread %d publish data success, value %s", Thread.currentThread().getId(), JSON.toJSONString(tradeDataUploadDTO)));
                    }
                }

            }else{
                try {
                    Thread.sleep(UploadConfig.UPLOADING_TIME_INTERVAL);
                } catch (InterruptedException e) {
                    logger.warn("Thread sleep exception." + e.getMessage() , e);
                }
            }
        }
    }


    @Override
    public void run() {
        handleMqtt();
    }
}
