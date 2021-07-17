package com.wml.dongbao.common.base.result;

import com.wml.dongbao.common.base.enums.StateCodeEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/16 - 07 - 16 - 13:33
 * @Description: com.wml.dongbao.common.base.result
 * @version: 1.0
 */
//统一返回值
@Data
@Builder//不停的去建造他自己，链式调用
public class ResultWrapper<T> implements Serializable {
    //ResultWrapper<T>就是一个类

    //状态码
    private int code;
    //提示信息
    private String msg;

    private T data;

    //返回成功的包装方法
    public static  ResultWrapper.ResultWrapperBuilder getSuccessBuilder()
    {
        return ResultWrapper.builder().code(StateCodeEnum.SUCCESS.getCode()).msg(StateCodeEnum.SUCCESS.getMsg());
    }

    //返回失败的包装方法
    public static  ResultWrapper.ResultWrapperBuilder getFailBuilder()
    {
        return ResultWrapper.builder().code(StateCodeEnum.FAIL.getCode()).msg(StateCodeEnum.FAIL.getMsg());
    }



}
