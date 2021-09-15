package com.sz.api.client;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.sz.api.RequestBean;
import com.sz.configs.ApiConst;
import lombok.Data;

/**
 * @auther: liaosz
 * @date: 2021/07/24/23:36
 * @description:
 */
@Data
@JsonIgnoreType
public class SyncTokenReq extends RequestBean<SyncTokenRes> {


    @Override
    public Class responseClass() {
        return SyncTokenRes.class;
    }

    @Override
    public String apiMethod() {
        return ApiConst.GET_SYNC_TOKEN;
    }
}
