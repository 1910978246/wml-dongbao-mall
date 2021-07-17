package com.wml.dongbao.ums.entity.response;

import com.wml.dongbao.ums.entity.UmsMember;
import lombok.Data;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/17 - 07 - 17 - 21:15
 * @Description: com.wml.dongbao.ums.entity.response
 * @version: 1.0
 */
@Data
public class UserMemberLoginResponse {
    private String token;
    private UmsMember umsMember;
}
