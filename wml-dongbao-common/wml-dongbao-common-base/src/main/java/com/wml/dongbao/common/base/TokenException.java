package com.wml.dongbao.common.base;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/18 - 07 - 18 - 1:08
 * @Description: com.wml.dongbao.common.base
 * @version: 1.0
 */
//自己定义异常
public class TokenException extends Exception{
    public TokenException(String message){
        super(message);
    }
}
