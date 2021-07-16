package com.wml.dongbao.common.base.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/16 - 07 - 16 - 13:23
 * @Description: com.wml.dongbao.common.base.enums
 * @version: 1.0
 * 100-199:用户业务的
 * 200-299：支付业务的
 * 以此类推
 */
//Enum:枚举 枚举类型 枚举类

@ToString
@AllArgsConstructor
public enum StateCodeEnum {
   /*
   * 定义
   * 请求成功
   */
    SUCCESS(200,"请求成功"),

    /*
     * 定义
     * 请求失败
     */
    FAIL(500,"请求失败");


    private  int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
