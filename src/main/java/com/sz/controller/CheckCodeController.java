package com.sz.controller;

import com.sz.api.ApiEnumError;
import com.sz.api.server.SendCheckCodeReq;
import com.sz.api.server.SendCheckCodeRes;
import com.sz.domain.CouponSendDoc;
import com.sz.exception.ServiceException;
import com.sz.service.CouponSendDocService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @auther: liaosz
 * @date: 2021/07/28/22:46
 * @description:
 */
@RestController
@Api(value = "优惠券发送接口" ,tags = "优惠券发送接口")
public class CheckCodeController {
    @Autowired
    private CouponSendDocService couponSendDocService;
    @ApiOperation(value = "同步优惠券信息",notes = "同步优惠券信息接口")
    @PostMapping("/sync/sendCheckCode")
    public SendCheckCodeRes checkCode(@RequestBody @Valid SendCheckCodeReq sendCheckCodeReq, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            HashMap<String, String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach( (item) -> {
                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put( field , message );
            } );
            return new SendCheckCodeRes().FAIL(ApiEnumError.INVALID_PARAMETER);
        }
        List<CouponSendDoc> couponSendDocList = new ArrayList<>();
        sendCheckCodeReq.getMember_info().forEach(menberinfo ->{
            CouponSendDoc couponSendDoc = new CouponSendDoc();
            BeanUtils.copyProperties(sendCheckCodeReq,couponSendDoc);
            BeanUtils.copyProperties(menberinfo,couponSendDoc);
            couponSendDocList.add(couponSendDoc);

        });
        if(couponSendDocList.isEmpty()){
            return new SendCheckCodeRes().FAIL(ApiEnumError.INVALID_PARAMETER);
        }

        couponSendDocService.saveCouponSendDocs(couponSendDocList);
        return new SendCheckCodeRes().SUCC();


    }
}
