package com.sz.adapter;

import com.sz.api.RequestBean;
import com.sz.api.ResponseBean;

import java.util.Map;

/**
 * .
 *
 * @Auther: liaosz
 * @Date: 2020/12/09/21:25
 * @Description:
 */
public interface IAdapter {
    public  <T extends ResponseBean> T send(RequestBean<T> requestBean, Map<String,Object> urlParam) throws Exception;

}
