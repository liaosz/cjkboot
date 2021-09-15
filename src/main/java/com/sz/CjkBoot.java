package com.sz;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.sz.api.client.SyncTokenReq;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @auther: liaosz
 * @date: 2021/07/18/12:25
 * @description:
 */
@SpringBootApplication//(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("com.sz.mapper")
@ImportResource("classpath:/spring/applicationContext-quartz.xml")
@EnableTransactionManagement //开启事务支持
public class CjkBoot {
    public static void main(String[] args) {
        SpringApplication.run(CjkBoot.class, args);
    }
}
