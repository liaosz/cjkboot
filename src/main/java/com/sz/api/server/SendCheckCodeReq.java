package com.sz.api.server;

import com.sz.api.RequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: liaosz
 * @date: 2021/07/28/10:29
 * @description:
 */
@Data
@ToString
@ApiModel(value = "优惠券同步模型",description = "用于批量同步")
public class SendCheckCodeReq extends RequestBean<SendCheckCodeRes> {
    @ApiModelProperty(value = "批次号",name = "batch_num",dataType = "string",required = true)
    @NotEmpty(message = "批次号不能为空")
    private String batch_num;
    @ApiModelProperty(value = "优惠券规则id",name = "coupon_id",dataType = "string",required = true)
    @NotEmpty(message = "优惠券规则id不能为空")
    private String coupon_id;
    @ApiModelProperty(value = "开始日期",name = "begin_date",dataType = "string",required = true)
    private String begin_date;
    @ApiModelProperty(value = "结束日期",name = "begin_date",dataType = "string",required = true)
    private String end_date;
    @ApiModelProperty(value = "会员信息",name = "member_info",dataType = "array",required = false)
    private List<MemberInfo> member_info = new ArrayList<>();
    @Override
    public Class<SendCheckCodeRes> responseClass() {
        return SendCheckCodeRes.class;
    }

    @Override
    public String apiMethod() {
        return null;
    }

    @Data
    @ToString
    @ApiModel(value = "会员信息",description = "同步会员信息")
    public static class MemberInfo {
        @ApiModelProperty(value = "会员卡号",name = "card_id",dataType = "string",required = true)
        private String card_id;
        @ApiModelProperty(value = "门店编码",name = "shop_id",dataType = "string",required = true)
        private String shop_id;
        @ApiModelProperty(value = "核销码",name = "shop_id",dataType = "string",required = true)
        private String check_code;
    }
}
