package com.wml.dongbao.ums.entity.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/15 - 07 - 15 - 21:50
 * @Description: com.wml.dongbao.ums.entity
 * @version: 1.0
 */
//接受前端传过来的参数
@Data
@ToString
public class UmsMemberRegisterParamDTO {//DTO:数据传输对象(Data Transfer Object)

    //@NotEmpty(message="用户不能为空") //spring validation 校验
    @Size(min = 1,max = 8,message = "用户名在1-8之间")
    private String username;

    private String password;

    private String icon;
    @Email
    private String email;

    private String nickName;
}
