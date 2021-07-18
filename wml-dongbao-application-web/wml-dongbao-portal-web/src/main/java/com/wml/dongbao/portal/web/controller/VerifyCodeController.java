package com.wml.dongbao.portal.web.controller;

import com.wml.dongbao.common.base.annotations.TokenCheck;
import com.wml.dongbao.portal.web.code.ImageCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/18 - 07 - 18 - 11:53
 * @Description: com.wml.dongbao.portal.web.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/code")
public class VerifyCodeController {
    String attrName = "verifyCode";

    @GetMapping("/generator")//生成验证码接口
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request , HttpServletResponse response){

        try {

            ImageCode imageCode = ImageCode.getInstance();

            //得到验证码的值
            String code = imageCode.getCode();

            request.getSession().setAttribute(attrName,code);

            //得到验证码的图片
            ByteArrayInputStream image = imageCode.getImage();

            //写出去
            response.setContentType("image/jpeg");
            byte[] bytes = new byte[1024];

            try(ServletOutputStream out = response.getOutputStream()) {
                while (image.read(bytes)!=-1){
                    out.write(bytes);
                }
            }
        }catch (Exception e){

            System.out.println("异常");

        }

    }

    @GetMapping("/verify")//校验验证码接口
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request){

        String s = request.getSession().getAttribute(attrName).toString();
        if (verifyCode.equals(s))
        {
            return "验证码校验通过";
        }
        return "验证码校验不通过";
    }

}