package com.sz.api.handler;

import com.sz.adapter.IAdapter;
import com.sz.api.ApiEnumError;
import com.sz.api.RequestBean;
import com.sz.api.ResponseBean;
import com.sz.api.client.CheckCodeStateReq;
import com.sz.api.client.CheckCodeStateRes;
import com.sz.api.client.SynCouponRulesReq;
import com.sz.api.client.SynCouponRulesRes;
import com.sz.domain.CouponStatusDoc;
import com.sz.domain.Coupondoc;
import com.sz.exception.ServiceException;
import com.sz.service.CouponStatusDocService;
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
public class CheckCodeStateHandlerImpl extends AbstractDataHandler {
    @Autowired
    private CouponStatusDocService couponStatusDocService;

    public CheckCodeStateHandlerImpl(IAdapter adapter) {
        super(adapter);
    }

    @Override
    public RequestBean befor(Map<String, Object> params) {
        CheckCodeStateReq checkCodeStateReq = null;

        final List<CouponStatusDoc> couponStatusDocs = couponStatusDocService.getCouponStatusDocs();

        if (couponStatusDocs == null || couponStatusDocs.isEmpty()) {
            return null;
        }
        checkCodeStateReq = new CheckCodeStateReq();
        for (CouponStatusDoc couponStatusDoc : couponStatusDocs) {
            CheckCodeStateReq.CheckCodeState couponRules = new CheckCodeStateReq.CheckCodeState();
            BeanUtils.copyProperties(couponStatusDoc, couponRules);
            checkCodeStateReq.getResult().add(couponRules);
        }

        return checkCodeStateReq;
    }

    @Override
    public boolean after(RequestBean requestBean, ResponseBean responseBean) {
        CheckCodeStateReq checkCodeStateReq = (CheckCodeStateReq) requestBean;
        CheckCodeStateRes checkCodeStateRes = (CheckCodeStateRes) responseBean;
        List<CouponStatusDoc> couponStatusDocs = new ArrayList<>();
        if (checkCodeStateRes.isSuccess()) {
            for (CheckCodeStateReq.CheckCodeState checkCodeState : checkCodeStateReq.getResult()) {
                CouponStatusDoc coupondoc = new CouponStatusDoc();
                BeanUtils.copyProperties(checkCodeState, coupondoc);
                couponStatusDocs.add(coupondoc);
            }

            couponStatusDocService.updateCouponStatusDocs(couponStatusDocs);

        }
        return true;
    }
}
