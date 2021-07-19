package com.wml.dongbao.portal.web.controller;

import com.ramostear.captcha.HappyCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import com.wml.dongbao.common.base.annotations.TokenCheck;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/19 - 07 - 19 - 18:46
 * @Description: com.wml.dongbao.portal.web.controller
 * @version: 1.0
 */

//easy-captcha验证码
@RestController
@RequestMapping("/easy-captcha")
public class EasyCaptchaController {

    @GetMapping("/generator")//生成验证码接口
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) {

        try {
            //1、基础
            //CaptchaUtil.out(request,response);

            //2、算数
            //ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(100,50);
            //3个数运算
            //arithmeticCaptcha.setLen(3);
            //结果
            //arithmeticCaptcha.text();
            //CaptchaUtil.out(arithmeticCaptcha,request,response);

            //3、中文
            ChineseCaptcha chineseCaptcha = new ChineseCaptcha(150,50);
            CaptchaUtil.out(chineseCaptcha,request,response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/verify")//校验验证码接口
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request){

        Boolean aBoolean = CaptchaUtil.ver(verifyCode,request);
        if (aBoolean)
        {
            //验证码的时效性
            HappyCaptcha.remove(request);
            return "验证码校验通过";
        }
        return "验证码校验不通过";
    }

}


