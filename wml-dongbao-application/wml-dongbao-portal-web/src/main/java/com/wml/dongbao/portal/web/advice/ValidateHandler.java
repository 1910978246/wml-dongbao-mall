package com.wml.dongbao.portal.web.advice;

import com.wml.dongbao.common.base.result.ResultWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/16 - 07 - 16 - 16:24
 * @Description: com.wml.dongbao.portal.web.advice
 * @version: 1.0
 */
//异常处理
@ControllerAdvice//同一异常处理
public class ValidateHandler extends ResponseEntityExceptionHandler {//应实体异常处理程序

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(//处理方法参数无效
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
            //参数校验的错误都走这个方法
            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError fieldError : ex.getBindingResult().getFieldErrors())
            {
                String defaultMessage = fieldError.getDefaultMessage();
                stringBuilder.append("" + defaultMessage);

                break;
            }
         // return new ResponseEntity<>(stringBuilder, HttpStatus.OK);
        return new ResponseEntity(ResultWrapper.builder().code(102).msg(stringBuilder.toString()).build(),HttpStatus.OK);
        }

    //这是一个main方法，是程序的入口：
    public static void main(String[] args) {
        int i = 1/0;
    }
}
