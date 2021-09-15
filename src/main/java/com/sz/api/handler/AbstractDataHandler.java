package com.sz.api.handler;

import com.sz.adapter.IAdapter;
import com.sz.api.ApiEnumError;
import com.sz.api.RequestBean;
import com.sz.api.ResponseBean;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * .
 *
 * @Auther: liaosz
 * @Date: 2020/12/09/21:28
 * @Description:
 */
@Slf4j
public abstract  class AbstractDataHandler implements IDataHandler {
    protected   IAdapter adapter;

    /**
     * 通讯前获取数据
     * @param params
     * @return
     */
    protected abstract RequestBean befor(Map<String,Object> params) throws Exception;

    /**
     *   通讯后数据处理
     * @param requestBean
     * @param responseBean
     * @return
     */
    protected abstract boolean after(RequestBean requestBean, ResponseBean responseBean) throws Exception;
    @Override
    public final  void process(Map<String,Object> params) {
        RequestBean requestBean = null;
        try {
            requestBean = befor(params);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("请求数据处理异常",e);
        }
        if (log.isInfoEnabled()) {
            log.info("完成请求数据处理");
        }
        if(requestBean != null) {
            ResponseBean responseBean = null;
            try {
                responseBean = adapter.send(requestBean,params);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (log.isInfoEnabled()) {
                log.info("响应代码：{}-{}" ,responseBean.getCode(),ApiEnumError.getResponseMsg(responseBean.getCode()));
            }
            boolean isSucc = false;
            try {
                isSucc = after(requestBean,responseBean);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("响应数据处理异常",e);
            }
            if(isSucc){
                if (log.isInfoEnabled()) {
                    log.info("完成响应数据处理");
                }
            }else{
                if (log.isInfoEnabled()) {
                    log.info("响应数据处理失败");
                }
            }
        }else{
            if (log.isInfoEnabled()) {
                log.info(this.getClass().getName() + ":没有数据需要同步");
            }
        }
    }


    public AbstractDataHandler(IAdapter adapter){
        this.adapter = adapter;
    }


}
