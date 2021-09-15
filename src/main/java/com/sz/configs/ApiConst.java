package com.sz.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @auther: liaosz
 * @date: 2021/07/24/23:38
 * @description:
 */
@Configuration
public class ApiConst {

    /* 获取授权码 token 接口 */
    public static String GET_SYNC_TOKEN;
    /* 优惠券规则同步接口 */
    public static String SYN_COUPON_RULES;
    /* 优惠券状态回执接口 */
    public static String CHECK_CODE_STATE;

    public static String token;

    public static Long timestamp;

    @Value("${api-config.getSyncToken}")
    public  void setGetSyncToken(String getSyncToken) {
        GET_SYNC_TOKEN = getSyncToken;
    }
    @Value("${api-config.synCouponRules}")
    public  void setSynCouponRules(String synCouponRules) {
        SYN_COUPON_RULES = synCouponRules;
    }
    @Value("${api-config.checkCodeState}")
    public  void checkCodeState(String checkCodeState) {
        CHECK_CODE_STATE = checkCodeState;
    }
}
