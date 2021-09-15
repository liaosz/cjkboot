package com.sz.adapter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sz.api.RequestBean;
import com.sz.api.ResponseBean;
import com.sz.api.client.SynCouponRulesRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

/**
 * @author liaosz
 * @date 2020/11/23 15:30
 */
@Component
@Slf4j
public class HttpJsonAdapter implements IAdapter{

    @Autowired
    private RestTemplate restTemplate;

    public  <T extends ResponseBean> T send(RequestBean<T> requestBean, Map<String,Object> urlParam) throws Exception{
        ResponseBean responseBean = null;
        try {
            if (log.isInfoEnabled()) {
                log.info("同步数据为:" + new ObjectMapper().writeValueAsString(requestBean));
            }

            //POST提交的头信息
            HttpHeaders headers = new HttpHeaders();//request header
            headers.setContentType(MediaType.APPLICATION_JSON);

            //组装报文实体
            HttpEntity<RequestBean> request = new HttpEntity<RequestBean>(requestBean, headers);

//          在RestTemplateConfig加入text/html  application/octet-stream转换成json支持
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(requestBean.apiMethod(), request,  String.class, urlParam);
           /* responseBean = responseEntity.getBody();
            if (log.isInfoEnabled()) {
                log.info("响应数据为:" + responseBean);
            }*/

            //服务端返回的是text/json的数据格式，只能通过string来获取。无法用对象获取
            String resStr = responseEntity.getBody();
            if(resStr != null){
                resStr = resStr.trim();
                if(resStr.startsWith("\"")){
                    resStr = resStr.substring(1);
                }
                if(resStr.endsWith("\"")){
                    resStr = resStr.substring(0,resStr.length()-1);
                }
                resStr = resStr.replaceAll("\\\\\"","\\\"");
            }
            if (log.isInfoEnabled()) {
                log.info("响应数据为:" + resStr);
            }
            ObjectMapper mapper = new ObjectMapper();

            responseBean = mapper.readValue(resStr, requestBean.responseClass());

            if (log.isInfoEnabled()) {
                log.info("响应数据为:" + responseBean);
            }


        } catch (IOException e) {
            if(log.isErrorEnabled()){
                log.error("通讯处理失败",e);
            }
            throw e;

        }

        return (T)responseBean;
    }

    public static void main(String[] args) throws JsonProcessingException {
        String resStr = "{\\\"msg\\\":\\\"SUCCESS\\\",\\\"code\\\":0}";
        System.out.println(resStr.replaceAll("\\\\\"","\\\""));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final SynCouponRulesRes synCouponRulesRes = mapper.readValue(resStr, SynCouponRulesRes.class);
        System.out.println(synCouponRulesRes);

//        System.out.println(responseBean);
    }

}
