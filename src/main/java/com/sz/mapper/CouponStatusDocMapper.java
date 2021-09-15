package com.sz.mapper;

import com.sz.domain.CouponStatusDoc;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther: liaosz
 * @date: 2021/08/18/16:38
 * @description:
 */
@Mapper
public interface CouponStatusDocMapper {
    /**
     * 获取需要同步的数据
     * @return
     */
    public List<CouponStatusDoc> getCouponStatusDocs();


    /**
     * 更新已经同步过的数据
     * @param couponStatusDocs
     * @return
     */
    public boolean updateCouponStatusDocs(List<CouponStatusDoc> couponStatusDocs);
}
