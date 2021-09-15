package com.sz.service;

import com.sz.domain.CouponSendDoc;
import com.sz.exception.ServiceException;

import java.util.List;

/**
 * @auther: liaosz
 * @date: 2021/08/17/0:06
 * @description:
 */
public interface CouponSendDocService {
    /**
     * 保存优惠券信息
     * @param couponSendDocs
     * @return
     */
    public boolean saveCouponSendDocs(List<CouponSendDoc> couponSendDocs);
}
