package com.sz.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: liaosz
 * @date: 2021/07/18/12:32
 * @description:
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(60000)).setReadTimeout(Duration.ofSeconds(60000));
        //先获取到converter列表
        List<HttpMessageConverter<?>> converters = restTemplateBuilder.build().getMessageConverters();
        for(HttpMessageConverter<?> converter : converters){
            //因为我们只想要jsonConverter支持对text/html的解析
            if(converter instanceof MappingJackson2HttpMessageConverter){
                try{
                    //先将原先支持的MediaType列表拷出
                    List<MediaType> mediaTypeList = new ArrayList<>(converter.getSupportedMediaTypes());
                    //加入对text/html的支持
                    mediaTypeList.add(MediaType.TEXT_HTML);
                    //加入对application/octet-stream的JSON转换支持
                    mediaTypeList.add(MediaType.APPLICATION_OCTET_STREAM);

                    //将已经加入了text/html的MediaType支持列表设置为其支持的媒体类型列表
                    ((MappingJackson2HttpMessageConverter) converter).setSupportedMediaTypes(mediaTypeList);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return restTemplateBuilder.build();

    }
}
