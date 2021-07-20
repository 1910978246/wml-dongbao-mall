package com.wml.dongbao.portal.web.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.ramostear.captcha.HappyCaptcha;
import com.wml.dongbao.common.base.annotations.TokenCheck;
import com.wml.dongbao.portal.web.custom.MyGoogleKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/20 - 07 - 20 - 8:58
 * @Description: com.wml.dongbao.portal.web.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {
    @Autowired
     private Kaptcha kaptcha;
    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response){
        kaptcha.render();


    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode,HttpServletRequest request) {

        /*try
        {
            Boolean aBoolean = kaptcha.validate(verifyCode,10);
            if (aBoolean) {
                //HappyCaptcha.remove(request);
                return "验证码校验通过";
        }
        }catch (KaptchaNotFoundException e)
        {
        }
        return "验证码校验不通过";*/


            Boolean aBoolean = kaptcha.validate(verifyCode,10);
            if (aBoolean) {

                return "验证码校验通过";
            }
        return "验证码校验不通过";
    }


    @Autowired
    MyGoogleKaptcha myGoogleKaptcha;

    @GetMapping("/generator-my")
    public void generatorCodeMy(HttpServletRequest request, HttpServletResponse response) {
        myGoogleKaptcha.render();
    }

    @GetMapping("/verify-my")
    public String verifyMy(String verifyCode, HttpServletRequest request) {

        Boolean aBoolean = myGoogleKaptcha.validate(verifyCode, 10);
        if (aBoolean) {
            return "通过";
        }
        return "不通过";
    }
}

