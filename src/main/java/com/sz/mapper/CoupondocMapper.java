package com.sz.mapper;

import com.sz.domain.Coupondoc;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * 优惠券表操作
 * @auther: liaosz
 * @date: 2021/07/24/22:58
 * @description:
 */
@Mapper
public interface CoupondocMapper {
    /**
     * 获取需要同步的数据
     * @return
     */
    public List<Coupondoc> getCoupondocs();


    /**
     * 更新已经同步过的数据
     * @param coupondocList
     * @return
     */
    public boolean updateCoupondocs(List<Coupondoc> coupondocList);
}
