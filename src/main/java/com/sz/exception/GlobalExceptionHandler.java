package com.sz.exception;





import com.sz.api.ApiEnumError;
import com.sz.api.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 * 把通过配置方式修改成注解方式
 * 配置方式通过实现HandlerExceptionResolver
 * 注解方式如下
 * @Auther: liaosz
 * @Date: 2020/04/03/16:41
 * @Description:
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(Exception exception){
        if(log.isErrorEnabled()){
            log.error("Exception",exception);
        }
        ResponseBean responseBean = new ResponseBean();
        responseBean.FAIL(ApiEnumError.EXCEPTION);
        return responseBean;
    }

}
