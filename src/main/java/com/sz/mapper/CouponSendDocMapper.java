package com.sz.mapper;

import com.sz.domain.CouponSendDoc;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther: liaosz
 * @date: 2021/08/16/23:20
 * @description:
 */
@Mapper
public interface CouponSendDocMapper {
    public boolean saveCouponSendDocs(List<CouponSendDoc> couponSendDocs);

    public boolean saveOrUpdateCouponSendDoc(CouponSendDoc couponSendDoc);
}
