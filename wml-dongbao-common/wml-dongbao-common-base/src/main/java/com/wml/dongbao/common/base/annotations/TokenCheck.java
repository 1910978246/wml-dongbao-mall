package com.wml.dongbao.common.base.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/17 - 07 - 17 - 23:25
 * @Description: com.wml.dongbao.common.base.annotations
 * @version: 1.0
 */
//写注解
@Target(ElementType.METHOD)//作用在方法上.ElementType:单元类型 、元素类型、 元素型别 、对象类型
@Retention(RetentionPolicy.RUNTIME)//
public @interface TokenCheck {
    //是否校验token
    boolean required() default true;
}
