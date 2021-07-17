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
//统一返回值
//Enum:枚举 枚举类型 枚举类
@ToString
@AllArgsConstructor
public enum StateCodeEnum {
     /*
     *用户密码不正确
     */
    PASSWORD_ERROR(1001,"用户密码不正确"),

    /*
     *用户不存在
     */

    USER_EMPTY(1002,"用户不存在"),

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

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
