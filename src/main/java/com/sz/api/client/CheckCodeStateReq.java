package com.sz.api.client;

import com.sz.api.RequestBean;
import com.sz.configs.ApiConst;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @auther: liaosz
 * @date: 2021/07/25/12:01
 * @description:
 */
@Data
@ToString
public class CheckCodeStateReq extends RequestBean<CheckCodeStateRes> {
    private List<CheckCodeState> result = new ArrayList<>();


    @Data
    @ToString
    public static class CheckCodeState{
        private String batch_num; // 批次号 String
        private String card_id; // 卡号 String
        private String check_code; // 核销码 String
        private String check_shop_id; // 核销门店 String
        private String use_time; // 优惠券使用时间 String 格式： yyyy-MM-dd hh:mm:ss
        private double all_amount; // 订单总额 decimal(10,2)
        private String bill_no; // 订单号 String
        private String name; // 收银员姓名 String

        public String getCard_id() {
            return card_id == null? null: card_id.trim();
        }
    }
    @Override
    public Class<CheckCodeStateRes> responseClass() {
        return CheckCodeStateRes.class;
    }

    @Override
    public String apiMethod() {
        return ApiConst.CHECK_CODE_STATE;
    }
}
