package Utility;

import com.alibaba.fastjson.JSON;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pojo.DeviceListResponseDTO;

public class RestfulUtility {
    public static Logger logger = Logger.getLogger(RestfulUtility.class);
    public static DeviceListResponseDTO getDeviceListFromUrl(String requestUrl , String apiKey){//apiKey 放在头部用于权限认证
        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key" , apiKey);
        HttpEntity<String> requestEntity = new HttpEntity<String>("" , headers);
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(requestUrl , HttpMethod.GET, requestEntity, String.class, "");
        } catch (RestClientException e) {
            logger.info("Restful service get request fail." + requestUrl);
            logger.trace("Stack trace: ",e);
            return null;
        }
        String jsonBody = responseEntity.getBody();
        DeviceListResponseDTO deviceListResponseDTO = null;
        try {
            deviceListResponseDTO = JSON.parseObject(jsonBody , DeviceListResponseDTO.class);
        } catch (Exception e) {
            logger.info("Response body parse to json object fail.");
            logger.trace("Stack trace: ",e);
            return null;
        }
        return deviceListResponseDTO;
    }
}
