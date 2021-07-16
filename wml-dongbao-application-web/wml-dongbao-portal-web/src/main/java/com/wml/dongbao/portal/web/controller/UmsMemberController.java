package com.wml.dongbao.portal.web.controller;

import com.wml.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.wml.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.wml.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public  String register(@RequestBody UmsMemberRegisterParamDTO umsMemberRegisterParamDTO)//接收前的参数
    {
        umsMemberService.register(umsMemberRegisterParamDTO);//register:注册帐户 寄存器 登记
        return "register";
    }

    @PostMapping("/login")//post新增get查询
    public  String login(@RequestBody UmsMemberLoginParamDTO umsMemberLoginParamDTO)//接收前的参数
    {
        return  umsMemberService.login(umsMemberLoginParamDTO);

    }
}

