package com.sz.service.impl;

import com.sz.domain.CouponSendDoc;
import com.sz.mapper.CouponSendDocMapper;
import com.sz.service.CouponSendDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther: liaosz
 * @date: 2021/08/17/0:07
 * @description:
 */
@Service
public class CouponSendDocServiceImpl implements CouponSendDocService {
    @Autowired
    private CouponSendDocMapper couponSendDocMapper;

    @Override
    @Transactional
    public boolean saveCouponSendDocs(List<CouponSendDoc> couponSendDocs) {
        /*couponSendDocs.forEach(couponSendDoc -> {
            couponSendDocMapper.saveOrUpdateCouponSendDoc(couponSendDoc);
        });*/
        couponSendDocMapper.saveCouponSendDocs(couponSendDocs);
        return true;
    }
}
