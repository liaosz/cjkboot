package com.sz.api.handler;

import com.sz.adapter.IAdapter;
import com.sz.api.RequestBean;
import com.sz.api.ResponseBean;
import com.sz.api.client.SynCouponRulesReq;
import com.sz.api.client.SynCouponRulesRes;
import com.sz.domain.Coupondoc;
import com.sz.exception.ServiceException;
import com.sz.service.CoupondocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther: liaosz
 * @date: 2021/07/27/9:30
 * @description:
 */
@Component
@Slf4j
public class CouponRulesHandlerImpl extends AbstractDataHandler {
    @Autowired
    private CoupondocService coupondocService;

    public CouponRulesHandlerImpl(IAdapter adapter) {
        super(adapter);
    }

    @Override
    public RequestBean befor(Map<String, Object> params) {
        SynCouponRulesReq synCouponRulesReq = null;


        final List<Coupondoc> coupondocs = coupondocService.getCoupondocs();

        if (coupondocs == null || coupondocs.isEmpty()) {
            return null;
        }
        synCouponRulesReq = new SynCouponRulesReq();
        for (Coupondoc coupondoc : coupondocs) {
            SynCouponRulesReq.CouponRules couponRules = new SynCouponRulesReq.CouponRules();
            BeanUtils.copyProperties(coupondoc, couponRules);
            synCouponRulesReq.getRules().add(couponRules);
        }

        return synCouponRulesReq;
    }

    @Override
    public boolean after(RequestBean requestBean, ResponseBean responseBean) {
        SynCouponRulesReq synCouponRulesReq = (SynCouponRulesReq) requestBean;
        SynCouponRulesRes synCouponRulesRes = (SynCouponRulesRes) responseBean;
        List<Coupondoc> coupondocs = new ArrayList<>();
        if (synCouponRulesRes.getCode() == 0) {
            for (SynCouponRulesReq.CouponRules rule : synCouponRulesReq.getRules()) {
                Coupondoc coupondoc = new Coupondoc();
                BeanUtils.copyProperties(rule, coupondoc);
                coupondocs.add(coupondoc);
            }

            coupondocService.updateCoupondocs(coupondocs);

        }
        return true;
    }
}
