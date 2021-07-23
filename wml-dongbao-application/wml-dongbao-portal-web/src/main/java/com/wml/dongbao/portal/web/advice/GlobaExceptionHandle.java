package com.wml.dongbao.portal.web.advice;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.wml.dongbao.common.base.TokenException;
import com.wml.dongbao.common.base.result.ResultWrapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/16 - 07 - 16 - 18:34
 * @Description: com.wml.dongbao.portal.web.advice
 * @version: 1.0
 */
//统一异常
@RestControllerAdvice
public class GlobaExceptionHandle {

    //@ExceptionHandler(Exception.class)
    @ExceptionHandler(ArithmeticException.class)//使异常更精细化,出现数学异常走这
    public ResultWrapper customException() {
        return ResultWrapper.builder().code(301).msg("统一异常").build();
    }

    /*
     * 使用package javax.security.auth.login;包里LoginException处理token异常
     * */
    /*@ExceptionHandler(LoginException.class)
    public ResultWrapper loginException(Exception e){
        return ResultWrapper.getFailBuilder().msg(e.getMessage()).build();
    }*/

    /*
     * 自定义token异常处理
     * */
    @ExceptionHandler(TokenException.class)
    public ResultWrapper TokenException(Exception e) {
        return ResultWrapper.getFailBuilder().code(501).msg(e.getMessage()).build();
    }

    /*
     * 自定义Kaptcha异常处理
     * */
    @ExceptionHandler(KaptchaException.class)
    public String KaptchaException(KaptchaException e) {
        if (e instanceof KaptchaTimeoutException) {
            return "超时";
        } else if (e instanceof KaptchaIncorrectException) {
            return "不正确";
        } else if (e instanceof KaptchaNotFoundException) {
            return "没找到";
        }else {
            return "反正错了";
        }
    }
}
