package com.wml.dongbao.portal.web.controller;

import com.wml.dongbao.common.base.annotations.TokenCheck;
import com.wml.dongbao.portal.web.util.SliderUtil;
import com.wml.dongbao.portal.web.util.VerificationVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/20 - 07 - 20 - 18:00
 * @Description: com.wml.dongbao.portal.web.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/slider")
public class MySliderController {
    @GetMapping("/generator")
    @TokenCheck(required = false)
    public VerificationVO generatorCode(HttpServletRequest request, HttpServletResponse response){

        return SliderUtil.cut();

    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode,HttpServletRequest request) {

        return "验证码校验不通过";
    }

}
