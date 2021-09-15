package com.sz.domain;

import lombok.Data;

/**
 * @auther: liaosz
 * @date: 2021/07/24/17:24
 * @description:
 */
@Data
public class Coupondoc {
    public String rq;    //优惠券制单日期 2021-07-08
    public String ontime; //优惠券制单时间 09:03:56
    public String username; // 操作员
    public String shop_id; //存健康提供 固定值

    public String shop_name; //门店名称

    public String coupon_id; // 优惠券编号
    public int coupon_type; //优惠券类型

    public String coupon_name; //优惠券名称
    //有效时间类型	int	0：领取后几天后到期（effective_days） 1：时间段（begin_date、end_date） *特殊说明：time_type=0 时，开始时间不是固定，开始时间取收到优惠券开始时计算。
    public int time_type;
    public int effective_days; //自领取起有效天数
    public String begin_date; //有效期开始时间
    public String end_date;//有效期结束时间；
    public String coupon_amount;//优惠金额/折扣/礼品名称

    public double limit_amount; //最低消费金额,满多少金额可用此券
    //例：全场通用，不限商品，购满 20元即可使用 1 张，本券不兑现、不设找零，券不可叠加使用
    public String coupon_desc;
    //    1：新增 2:删除 3：停用 4：修改 5：启用
    public int type;

    public int quantity; //优惠券数量

    public String remark; //备注

    public String lastmodifytime; //末次修改时间

    public String is_up; //是否上传  是：已上传

    public String upmodifytime; //上传修改时间

}
