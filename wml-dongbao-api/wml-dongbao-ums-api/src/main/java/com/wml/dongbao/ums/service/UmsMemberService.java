package com.wml.dongbao.ums.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wml.dongbao.ums.entity.UmsMember;
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
    public String register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO);

    //登录
    public String login(UmsMemberLoginParamDTO umsMemberLoginParamDTO);

}
