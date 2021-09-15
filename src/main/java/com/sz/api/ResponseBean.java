package com.sz.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * .
 *
 * @Auther: liaosz
 * @Date: 2020/11/17/21:25
 * @Description:
 */
@Data
@ApiModel(value = "响应父模型",description = "响应状态码")
public class ResponseBean<T extends ResponseBean> implements IResponse, Serializable {

    private static final long serialVersionUID = -4310799230060969230L;
    @ApiModelProperty(value = "状态码",name = "code",dataType = "string",required = true)
    protected int code;
    @ApiModelProperty(value = "响应信息",name = "msg",dataType = "string",required = true)
    protected String msg;

    public  T SUCC(){
        this.code = 0;
        this.msg = ApiEnumError.getResponseMsg(this.code);
        return (T) this;
    }

    public T FAIL(int code){
        this.code = code;
        this.msg = ApiEnumError.getResponseMsg(this.code);
        return (T) this;
    }
    public T FAIL(int code,String msg){
        this.code = code;
        this.msg = msg;
        return (T)this;
    }

    public T FAIL(ApiEnumError enumError){
        this.code = enumError.getCode();
        this.msg = enumError.getMsg();
        return (T)this;
    }
    @JsonIgnore
    public boolean isSuccess(){
        return this.code == ApiEnumError.SUCCESS.getCode();
    }

}
