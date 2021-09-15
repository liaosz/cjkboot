package com.sz.domain;

import lombok.Data;

/**
 * @auther: liaosz
 * @date: 2021/08/18/16:35
 * @description:
 */
@Data
public class CouponStatusDoc {
    private String batch_num; // 批次号 String
    private String card_id; // 卡号 String
    private String check_code; // 核销码 String
    private String check_shop_id; // 核销门店 String
    private String use_time; // 优惠券使用时间 String 格式： yyyy-MM-dd hh:mm:ss
    private double all_amount; // 订单总额 decimal(10,2)
    private String bill_no; // 订单号 String
    private String name; // 收银员姓名 String
    private String is_up; //是否上传
    private String upmodifytime; //上传修改时间
}
