package com.wml.dongbao.portal.web.controller;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.wml.dongbao.common.base.annotations.TokenCheck;
import com.wml.dongbao.portal.web.util.JCaptchaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/18 - 07 - 18 - 11:53
 * @Description: com.wml.dongbao.portal.web.controller
 * @version: 1.0
 */
//JCaptch验证码
@RestController
@RequestMapping("/jcaptcha")
public class JCaptchController {
    String attrName = "verifyCode";

    @GetMapping("/generator")//生成验证码接口
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) {
        try
        {
            String id = request.getSession().getId();

            BufferedImage bufferedImage = JCaptchaUtil.getService().getImageChallengeForID(id);

            //创建图片编辑码
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(byteArrayOutputStream);

            jpegEncoder.encode(bufferedImage);

            Object attribute = request.getSession().getAttribute(attrName);
            System.out.println(attribute);

            response.setHeader("Cache-Control","no-store");
            response.setContentType("image/jpeg");

            //把byteArrayOutputStream转化成字节数组
            byte[] bytes = byteArrayOutputStream .toByteArray();
            //得到一个ServletOutputStream
            ServletOutputStream servletOutputStream = response.getOutputStream();

            servletOutputStream.write(bytes);
            servletOutputStream.flush();
            servletOutputStream.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    @GetMapping("/verify")//校验验证码接口
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request){

        Boolean aBoolean = JCaptchaUtil.getService().validateResponseForID(request.getSession().getId(), verifyCode);
        if (aBoolean)
        {
            return "验证码校验通过";
        }


        return "验证码校验不通过";
    }

}
