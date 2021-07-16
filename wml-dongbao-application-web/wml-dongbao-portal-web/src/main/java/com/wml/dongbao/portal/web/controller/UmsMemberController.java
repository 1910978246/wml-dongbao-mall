package com.wml.dongbao.portal.web.controller;

import com.wml.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.wml.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String hello(){
        return "hello";
    }

    @GetMapping("/register")
    public  String register(@RequestBody UmsMemberRegisterParamDTO umsMemberRegisterParamDTO){//接收前的参数
        umsMemberService.register(umsMemberRegisterParamDTO);//register:注册帐户 寄存器 登记
        return "register";
    }

}
