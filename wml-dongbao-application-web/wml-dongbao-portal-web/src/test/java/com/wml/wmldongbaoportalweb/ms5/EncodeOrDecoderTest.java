package com.wml.wmldongbaoportalweb.ms5;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/16 - 07 - 16 - 9:37
 * @Description: com.wml.wmldongbaoportalweb.ms5
 * @version: 1.0
 *
 */
//脱敏:脱掉敏感信息的测试
//解决方法：
public class EncodeOrDecoderTest {//Encode:编码 译成密码 Decoder:译码器 解码器
    //方法一：使用md5
    //  md5(原始值)=m1
    //彩虹表攻击。（md5值===原始值）对应表。（md5（原始值）每次生成的值都一样）
    @Test
    public void encodeAndDecode(){
        String sourceString = "123456";
        String s = DigestUtils.md5DigestAsHex(sourceString.getBytes());
        //验证
        //if (s==数据库里的密码){}
        System.out.println("第一次加密值"+s);
        s = DigestUtils.md5DigestAsHex(sourceString.getBytes());
        System.out.println("第二次加密值"+s);
    }

    //第二种：哈希算法进行加密,BCrypt密码加密
    @Test
    public void bcrypt(){
        //要加密的原密码
        String sourceString = "123456";
        //加密密码
        BCryptPasswordEncoder bCryptPasswordEncoder  = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(sourceString);
        System.out.println("第一次加密值"+encode);

        //登录后的验证
        boolean matches = bCryptPasswordEncoder.matches(sourceString, encode);//matches:验证
        System.out.println("第一次验证"+matches);

        encode = bCryptPasswordEncoder.encode(sourceString);
        System.out.println("第二次加密值"+encode);
        matches = bCryptPasswordEncoder.matches(sourceString, encode);
        System.out.println("第二次验证"+matches);
    }
}
