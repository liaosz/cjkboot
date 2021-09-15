package com.sz.service.impl;

import com.sz.domain.CouponStatusDoc;
import com.sz.mapper.CouponStatusDocMapper;
import com.sz.service.CouponStatusDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: liaosz
 * @date: 2021/08/18/16:51
 * @description:
 */
@Service
public class CouponStatusDocServiceImpl implements CouponStatusDocService {
    @Autowired
    private CouponStatusDocMapper couponStatusDocMapper;
    /**
     * 获取需要同步的数据
     * @return
     */
    public List<CouponStatusDoc> getCouponStatusDocs(){
        return couponStatusDocMapper.getCouponStatusDocs();
    }


    /**
     * 更新已经同步过的数据
     * @param couponStatusDocs
     * @return
     */
    public boolean updateCouponStatusDocs(List<CouponStatusDoc> couponStatusDocs){
        couponStatusDocMapper.updateCouponStatusDocs(couponStatusDocs);
        return true;
    }
}
