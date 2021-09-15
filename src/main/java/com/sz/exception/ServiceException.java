package com.sz.exception;

/**
 * .
 *
 * @Auther: liaosz
 * @Date: 2020/04/03/11:17
 * @Description:
 */
public class ServiceException extends Exception{
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException() {
        super();
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message) {
        super(message);
    }
}
