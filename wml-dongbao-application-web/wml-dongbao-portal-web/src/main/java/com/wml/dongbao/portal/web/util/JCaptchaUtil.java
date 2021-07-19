package com.wml.dongbao.portal.web.util;

import com.octo.captcha.CaptchaFactory;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;

import java.awt.*;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/19 - 07 - 19 - 11:30
 * @Description: com.wml.dongbao.portal.web.util
 * @version: 1.0
 */
//自定义工具类JCaptcha

public class JCaptchaUtil {

    private static final ImageCaptchaService service = imageCaptchaService();

    public static ImageCaptchaService getService(){

        return  service;
    }

    //用ImageCaptchaService来画图
    private static ImageCaptchaService imageCaptchaService(){
        //纯色的背景
        UniColorBackgroundGenerator backgroundGenerator = new UniColorBackgroundGenerator(100,50);

        //字
        //字的随机颜色生成器
        RandomRangeColorGenerator textColor = new RandomRangeColorGenerator(new int[]{0,100},new int[]{0,200},new int[]{50,250});

        RandomTextPaster randomTextPaster = new RandomTextPaster(4 ,4,textColor);
        //字体的形状
        RandomFontGenerator randomFontGenerator = new RandomFontGenerator(20,30,new Font[]{new Font("TimesRoman", Font.PLAIN,20)});

        //组装图像
        ComposedWordToImage composedWordToImage = new ComposedWordToImage(randomFontGenerator,backgroundGenerator,randomTextPaster);

        //字典
        ComposeDictionaryWordGenerator composeDictionaryWordGenerator = new ComposeDictionaryWordGenerator(new FileDictionary("toddlist"));

        //
        GimpyFactory gimpyFactory = new GimpyFactory(composeDictionaryWordGenerator,composedWordToImage);

        //生成Captcha引擎
        GenericCaptchaEngine genericCaptchaEngine = new GenericCaptchaEngine(new CaptchaFactory[]{gimpyFactory});


        return new GenericManageableCaptchaService(genericCaptchaEngine,30,2000,2000);
    }


}

