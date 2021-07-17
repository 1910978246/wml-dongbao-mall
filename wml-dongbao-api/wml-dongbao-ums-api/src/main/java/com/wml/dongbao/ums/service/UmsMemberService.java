package com.wml.dongbao.ums.service;


import com.wml.dongbao.common.base.result.ResultWrapper;
import com.wml.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.wml.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;


/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author 王明礼
 * @since 2021-07-13
 */
public interface UmsMemberService {
    //注册
    public ResultWrapper register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO);

    //登录
    public ResultWrapper login(UmsMemberLoginParamDTO umsMemberLoginParamDTO);

}
