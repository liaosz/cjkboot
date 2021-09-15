package com.sz.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @auther: liaosz
 * @date: 2021/06/24/23:31
 * @description:
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    //http://localhost:8080/swagger-ui/index.html
    @Bean
    public Docket docket(){
        ApiInfo apiInfo = new ApiInfo("Api Documentation", "Api Documentation", "v1.0", "urn:tos", new Contact("liaosz","http://localhost","273644600@qq.com"), "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sz.controller")).build();
    }
}
