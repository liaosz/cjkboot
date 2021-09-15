package com.sz.service;

import com.sz.domain.Coupondoc;
import com.sz.exception.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: liaosz
 * @date: 2021/07/27/9:34
 * @description:
 */

public interface CoupondocService {
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
