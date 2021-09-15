package com.sz.api.client;

import com.sz.api.ResponseBean;
import lombok.Data;
import lombok.ToString;

/**
 * @auther: liaosz
 * @date: 2021/07/24/23:36
 * @description:
 */
@Data
@ToString(callSuper = true)
public class SyncTokenRes extends ResponseBean {
    private String token;
    private Long timestamp;
    private String expiresin;

}
