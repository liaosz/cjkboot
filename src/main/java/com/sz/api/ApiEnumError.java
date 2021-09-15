package com.sz.api;

public enum ApiEnumError {
    //网络超时
    SUCCESS(0, "SUCCESS"),
    //网络链接异常
    INVALID_TOKEN(1, "INVALID_TOKEN"),
    //请求参数错误
    INVALID_PARAMETER(2, "INVALID_PARAMETER"),
    //服务器端报错
    EXCEPTION(3, "EXCEPTION");



    private int code;
    private String msg;

    private ApiEnumError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getResponseMsg(int code) {
        for (ApiEnumError apiEnumError : ApiEnumError.values()) {
            if (code == apiEnumError.getCode()) {
                return apiEnumError.getMsg();
            }
        }
        return EXCEPTION.getMsg();
    }


}
