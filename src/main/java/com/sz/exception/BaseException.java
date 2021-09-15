package com.sz.exception;

/**
 * .
 *
 * @Auther: liaosz
 * @Date: 2020/07/07/23:14
 * @Description:
 */
public class BaseException extends RuntimeException {
    private String code;
    private String msg;

    public BaseException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(Throwable cause, String code, String msg) {
        super(msg,cause);
        this.code = code;
        this.msg = msg;
    }

    public BaseException() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
