package mqtt;

import Constant.Constant;
import com.alibaba.fastjson.JSON;

import config.UploadConfig;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import pojo.BusDeviceDataDO;
import pojo.DeviceInfo;

import javax.naming.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * MqttClient class
 *
 * @author Carson
 * @date 11/27/2017
 * refactor 10/12/2018
 */
public class MqttDevice {

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

    private BusDeviceDataDO busDeviceDataDO;

    public MqttDevice(MqttConnectOptions option, String deviceId , BusDeviceDataDO busDeviceDataDO) {
        this.option = option;
        this.deviceId = deviceId;
        this.busDeviceDataDO = busDeviceDataDO;
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

    public void handleMqtt() {
        if(busDeviceDataDO == null) {
            logger.warn("BusDeviceDataDO equals null");
        }else {
            connect();
            if(mqttClient.isConnected()) {
                //upload data
                boolean success = publish(Constant.MQTT_SYSTEM_TOPIC, JSON.toJSONString(busDeviceDataDO));
                if (success) {
                    logger.debug(String.format("Publish data success, value %s", JSON.toJSONString(busDeviceDataDO)));
                }else{
                    logger.warn(String.format("Publish data fail , value %s", JSON.toJSONString(busDeviceDataDO)));
                }

                try {
                    mqttClient.close();
                } catch (MqttException e) {
                    logger.error("mqtt close throw exception " , e);
                    throw new RuntimeException("Can not close the mqttClient by close method.");
                }
            }else {
                logger.warn("mqtt connect fail.");
            }
        }
    }

}
