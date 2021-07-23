package com.wml.dongbao.ums;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/13 - 07 - 13 - 21:04
 * @Description: com.wml.wmldongbaoums
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan("com.wml.dongbao.ums.mapper")
public class WmlDongbaoUmsApplication {
    //这是一个main方法，是程序的入口：
    public static void main(String[] args) {
        SpringApplication.run(WmlDongbaoUmsApplication.class,args);
    }
    //注入项目同一用PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
