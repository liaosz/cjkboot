package com.sz.quartz;

import cn.hutool.core.date.DateUtil;
import com.sz.api.handler.CheckCodeStateHandlerImpl;
import com.sz.api.handler.CouponRulesHandlerImpl;
import com.sz.api.handler.TokenHandlerImpl;
import com.sz.configs.ApiConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * 通过XML配置定时任务
 */
public class QuartzSync {
    private static final Logger logger = LoggerFactory.getLogger(QuartzSync.class);


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenHandlerImpl tokenHandler;

    @Autowired
    private CouponRulesHandlerImpl couponRulesHandler;
    @Autowired
    private CheckCodeStateHandlerImpl checkCodeStateHandler;
    private String starttime = "2020-01-01 00:00:00";

    public void syncTask() {
        String endtime = DateUtil.now();
        if (logger.isInfoEnabled()) {
            logger.info("同步数据时间范围：{} 到 {}", starttime, endtime);
        }
        tokenHandler.process(new HashMap<>());
        HashMap params = new HashMap();
        params.put("token", ApiConst.token);
        params.put("timestamp", ApiConst.timestamp);
        couponRulesHandler.process(params);

        checkCodeStateHandler.process(params);

        starttime = endtime;
    }


}
