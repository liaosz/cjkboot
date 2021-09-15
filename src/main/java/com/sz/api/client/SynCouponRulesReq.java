package com.sz.api.client;

import com.sz.api.RequestBean;
import com.sz.configs.ApiConst;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: liaosz
 * @date: 2021/07/25/9:48
 * @description:
 */


@Data
@ToString
public class SynCouponRulesReq extends RequestBean<SynCouponRulesRes> {
    private List<CouponRules> rules = new ArrayList<>();
    @Override
    public Class responseClass() {
        return SynCouponRulesRes.class;
    }

    @Override
    public String apiMethod() {
        return ApiConst.SYN_COUPON_RULES;
    }

    @Data
    @ToString
    public static class CouponRules{
        private String coupon_id; //优惠券编号
        private int coupon_type; //优惠券类型  1.代金券 2.折扣券 3.礼品券
        private String coupon_name; //优惠券名称
        /*有效时间类型 Int 0：领取后几天后到期（effective_days）1：时间段（begin_date、 end_date）
           *特殊说明： time_type=0 时，开始时间不是固定，开始时间取收到优惠券开始时计算。*/
        private int time_type;
        private int effective_days;// 自领取起有效天数 Int
        private String begin_date; // 有效期开始时间 String 格式： yyyy-MM-dd
        private String end_date; // 有效期结束日期 String 格式： yyyy-MM-dd
        private String coupon_amount; // 优惠金额/折扣/礼品名称 String
        private double limit_amount; // 最低消费金额 decimal(10,2) 满多少金额可用此券
        private String coupon_desc; // 优惠券描述 String 例：全场通用，不限商品，购满 20元即可使用 1 张，本券不兑现、不设找零，券不可叠加使用
        private int type; //同步类型 Int 1：新增 2:删除 3：停用 4：修改 5：启用
        private int quantity; //优惠券数量 Int
    }
}
