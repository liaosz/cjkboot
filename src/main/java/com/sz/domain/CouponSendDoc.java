package com.sz.domain;

import lombok.Data;

/**
 * @auther: liaosz
 * @date: 2021/08/16/23:08
 * @description:
 */
@Data
public class CouponSendDoc {
    /*批次号*/
    private String batch_num;
    /*优惠券规则 id*/
    private String coupon_id;
    /*会员卡号*/
    private String card_id;
    /*会员所属门店编码*/
    private String shop_id;
    /*核销码*/
    private String check_code;
    /*有效开始时间*/
    private String begin_date;
    /*有效结束时间*/
    private String end_date;
}
