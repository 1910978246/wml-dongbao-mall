package com.wml.dongbao.portal.web.controller.studyCaptcha;

import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import com.wml.dongbao.common.base.annotations.TokenCheck;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Auther: 王明礼
 * @Date: 2021/7/18 - 07 - 18 - 11:53
 * @Description: com.wml.dongbao.portal.web.controller
 * @version: 1.0
 */
//happy-captcha验证码
@RestController
@RequestMapping("/happy-captcha")
public class HappyCaptchaController {

    @GetMapping("/generator")//生成验证码接口
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) {

        HappyCaptcha.require(request,response)
                .type(CaptchaType.ARITHMETIC_ZH)
                //.type(CaptchaType.NUMBER_ZH_HK)
                .style(CaptchaStyle.ANIM)
                .build().finish();
    }


    @GetMapping("/verify")//校验验证码接口
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request){

        Boolean aBoolean = HappyCaptcha.verification(request,verifyCode,true);//忽略大小写
        if (aBoolean)
        {
            //验证码的时效性
            HappyCaptcha.remove(request);
            return "验证码校验通过";
        }

        return "验证码校验不通过";
    }

}
