package com.wml.dongbao.portal.web.controller;

import com.wml.dongbao.common.base.annotations.TokenCheck;
import com.wml.dongbao.common.base.result.ResultWrapper;
import com.wml.dongbao.ums.entity.UmsMember;
import com.wml.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.wml.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.wml.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/15 - 07 - 15 - 11:38
 * @Description: com.wml.dongbao.potal.web.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/user-member")
public class UmsMemberController {
    @Autowired
    UmsMemberService umsMemberService;

    //测试代码是否能正常运行用的
    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }

    @PostMapping("/register")
    public ResultWrapper register(@RequestBody @Valid UmsMemberRegisterParamDTO umsMemberRegisterParamDTO)//接收前的参数
    {
        //如果不使用validateHander的话要写判断
      /* String email = UmsMemberRegisterParamDTO.getEmail();
       if (email=="aaas")
       {
           return "不符合邮件格式";
       }
       if (umsMemberRegisterParamDTO.getUsername().length()>1&&umsMemberRegisterParamDTO.getUsername()<8)
       {
           return ""
       }*/

        //umsMemberService.register(umsMemberRegisterParamDTO);//register:注册帐户 寄存器 登记

        //简单状态码的方法写
        /*ResultWrapper<Object> resultWrapper = new ResultWrapper<>();
        resultWrapper.setCode(200);
        resultWrapper.setMsg("请求成功");
        resultWrapper.setData("");
        return resultWrapper;*/

        //使用builder的写状态码方法
        return umsMemberService.register(umsMemberRegisterParamDTO);
    }


    @PostMapping("/login")//post新增get查询
    public  ResultWrapper login(@RequestBody  UmsMemberLoginParamDTO umsMemberLoginParamDTO)//接收前的参数
    {
        return  umsMemberService.login(umsMemberLoginParamDTO);

    }

    //测试token在过期时间内是否能登陆上去
    //用这个接口代表了所有的后期需要鉴权的请求
    //修改用户信息
    //这是测试的   系统中的任意一个接口
    /*@GetMapping("/test-verify")
    public String verify(String token){
        String s = JwtUtil.parseToken(token);
        String token1 = JwtUtil.creatToken(s);//解析完token有效在创建一个token，token延期
        return token1;
    }*/

    //修改用户信息
    @GetMapping("/test-verify")
    public ResultWrapper verify(String token){

        System.out.println("正常业务");
        return ResultWrapper.getSuccessBuilder().build();
    }

    @PostMapping("/edit")
    @TokenCheck//加注解拦截方法
    public ResultWrapper edit(@RequestBody UmsMember umsMember){

        System.out.println("edit");

        return umsMemberService.edit(umsMember);
    }




}

