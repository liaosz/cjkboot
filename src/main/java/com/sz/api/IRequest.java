package com.sz.api;

/**
 * .
 *
 * @Auther: liaosz
 * @Date: 2020/12/06/11:10
 * @Description:
 */
public interface IRequest <T extends ResponseBean>{

    Class<T> responseClass();

    String apiMethod();
}
