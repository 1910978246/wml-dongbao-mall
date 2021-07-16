package com.wml.dongbao.ums.entity.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/15 - 07 - 15 - 21:50
 * @Description: com.wml.dongbao.ums.entity
 * @version: 1.0
 */
//登录
@Data
@ToString
public class UmsMemberLoginParamDTO {//DTO:数据传输对象(Data Transfer Object)
    private String username;
    private String password;

}
