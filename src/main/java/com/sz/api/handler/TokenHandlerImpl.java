package com.sz.api.handler;

import com.sz.adapter.IAdapter;
import com.sz.api.RequestBean;
import com.sz.api.ResponseBean;
import com.sz.api.client.SyncTokenReq;
import com.sz.api.client.SyncTokenRes;
import com.sz.configs.ApiConst;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @auther: liaosz
 * @date: 2021/07/26/22:40
 * @description:
 */
@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class TokenHandlerImpl extends AbstractDataHandler {


    @Override
    protected RequestBean befor(Map<String, Object> params) {
        if(log.isInfoEnabled()){
            log.info("开始获取token信息");
        }
        return new SyncTokenReq();
    }

    @Override
    protected boolean after(RequestBean requestBean, ResponseBean responseBean) {
        SyncTokenRes syncTokenRes = (SyncTokenRes) responseBean;
        ApiConst.token = syncTokenRes.getToken();
        ApiConst.timestamp = syncTokenRes.getTimestamp();
        return true;
    }

    @Autowired
    public TokenHandlerImpl(IAdapter adapter) {
        super(adapter);
    }
}
