package com.wml.dongbao.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/16 - 07 - 16 - 22:32
 * @Description: com.wml.dongbao.common.until
 * @version: 1.0
 *
 */
//token在登录的时候做

public class JwtUtil {

    private static final String secret = "abcdefgh";

    public static  String creatToken(String subject)//主题:拿用户名、用户ID生成一个token
    {
        String token = Jwts.builder().setSubject(subject)
                //.setExpiration(new Date(new Date().getTime()+1000*3))
                .setExpiration(new Date(System.currentTimeMillis()+1000*100))//设置过期时间3秒

                .signWith(SignatureAlgorithm.HS256, secret)

                .compact();

        return token;
    }

    //解析token
    public static String parseToken(String token) {

        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        String dubject = body.getSubject();

        return dubject;

    }


    //这是一个main方法，是程序的入口：
    public static void main(String[] args) throws InterruptedException {
        String name = "小花";
        //生成token
        String token = creatToken(name);
        System.out.println("token:"+token);


        //调取解析token
        String srcStr = parseToken(token);
        System.out.println("解析出来："+srcStr);

        TimeUnit.SECONDS.sleep(4);//睡眠4秒
        srcStr = parseToken(token);
        System.out.println("解析出来："+srcStr);

    }

    /*
    * base64是编码器可以编码，可以解码
    * eyJhbGciOiJIUzI1NiJ9.(使用 base64.encode(header)进行编码得到一个值) 拿header里的算法对载荷进行加密生成一个盐
    * eyJzdWIiOiLlsI_oirEifQ.(base64.encode(payload) 载荷，信息用户名，用户ID)
    * zsAQQ16jhgDFKk8LZUckp_JvbJG9K2SNAfTVhW5KBpo(散列加密(payload,盐(secret))签名) secret:在我的本地谁也拿不去只有开发者知道
    * */

}
