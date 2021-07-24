package com.wml.dongbao.ums.service.impl;

import com.wml.dongbao.common.base.enums.StateCodeEnum;
import com.wml.dongbao.common.base.result.ResultWrapper;
import com.wml.dongbao.common.util.JwtUtil;
import com.wml.dongbao.ums.entity.UmsMember;
import com.wml.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.wml.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.wml.dongbao.ums.entity.response.UserMemberLoginResponse;
import com.wml.dongbao.ums.mapper.UmsMemberMapper;
import com.wml.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author 王明礼
 * @since 2021-07-13
 *
 * 业务逻辑在serivce层
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
   //这是service写死了代码
   /* @Autowired
    UmsMemberMapper umsMemberMapper;
    public String register(){
        UmsMember u = new UmsMember();
        u.setNickName("c");
        umsMemberMapper.insert(u);
        return "success";
    }*/

    //将service写活，这这就涉及到分两个层的概念。
    // 第一个：service中的bean（程序中代码处理的bean），第二个：接收到用户的参数（接受前端传过来的参数）。
    //所以要在做一个bean（专门给前端写的bean）一般定义到api的dto中
    @Autowired
    UmsMemberMapper umsMemberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override//注册
    public ResultWrapper register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO)
    {
        //要把UmsMemberRegisterParamDTO这个bean转化成数据库要存的bean
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberRegisterParamDTO,umsMember);//(原路径,目标对象)

        //加密密码
        //用了spring就不用自己去new BCryptPasswordEncoder
        String encode = passwordEncoder.encode(umsMemberRegisterParamDTO.getPassword());
        umsMember.setPassword(encode);

        umsMemberMapper.insert(umsMember);//把目标对象存储进来
        return ResultWrapper.getSuccessBuilder().build();
    }

    @Override//登录
    public ResultWrapper login(UmsMemberLoginParamDTO umsMemberLoginParamDTO)
    {
        UmsMember umsMember = umsMemberMapper.selectByName(umsMemberLoginParamDTO.getUsername());//查询username
        if (null != umsMember)
        {
            String passwordDb = umsMember.getPassword();//数据库里的原始密码
            //判断密码
            if (!passwordEncoder.matches(umsMemberLoginParamDTO.getPassword(),passwordDb))
            {
                return ResultWrapper.getFailBuilder().code(StateCodeEnum.PASSWORD_ERROR.getCode()).msg(StateCodeEnum.PASSWORD_ERROR.getMsg()).build();
            }
        }else
            {
                return ResultWrapper.getFailBuilder().code(StateCodeEnum.USER_EMPTY.getCode()).msg(StateCodeEnum.USER_EMPTY.getMsg()).build();
            }

        //String  token = JwtUtil.creatToken(umsMember.getPassword());//token
        String  token = JwtUtil.creatToken(umsMember.getId()+"");

        UserMemberLoginResponse userMemberLoginResponse = new UserMemberLoginResponse();
        userMemberLoginResponse.setToken(token);
        umsMember.setPassword("");
        userMemberLoginResponse.setUmsMember(umsMember);
        return ResultWrapper.getSuccessBuilder().data(userMemberLoginResponse).build();
    }

    @Override//修改用户信息
    public ResultWrapper edit(UmsMember umsMember) {
        umsMemberMapper.updateById(umsMember);
        return ResultWrapper.getSuccessBuilder().data(umsMember).build();
    }

}
