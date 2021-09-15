package com.sz.service;

import com.sz.domain.Coupondoc;
import com.sz.exception.ServiceException;
import com.sz.mapper.CoupondocMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: liaosz
 * @date: 2021/07/27/9:37
 * @description:
 */
@Service
public class CoupondocServiceImpl implements CoupondocService {
    @Autowired
    private CoupondocMapper coupondocMapper;
    @Override
    public List<Coupondoc> getCoupondocs() throws ServiceException {
        return coupondocMapper.getCoupondocs();
    }

    @Override
    public boolean updateCoupondocs(List<Coupondoc> coupondocList) throws ServiceException {
        return coupondocMapper.updateCoupondocs(coupondocList);
    }
}
