package com.wml.dongbao.portal.web.controller;

import com.wml.dongbao.common.base.result.ResultWrapper;
import com.wml.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.wml.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.wml.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

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

        umsMemberService.register(umsMemberRegisterParamDTO);//register:注册帐户 寄存器 登记

        //简单状态码的方法写
        /*ResultWrapper<Object> resultWrapper = new ResultWrapper<>();
        resultWrapper.setCode(200);
        resultWrapper.setMsg("请求成功");
        resultWrapper.setData("");
        return resultWrapper;*/

        //使用builder的写状态码方法
        return ResultWrapper.getSuccessBuilder().data(null).build();
    }

    @PostMapping("/login")//post新增get查询
    public  String login(@RequestBody  UmsMemberLoginParamDTO umsMemberLoginParamDTO)//接收前的参数
    {
        return  umsMemberService.login(umsMemberLoginParamDTO);

    }




}

