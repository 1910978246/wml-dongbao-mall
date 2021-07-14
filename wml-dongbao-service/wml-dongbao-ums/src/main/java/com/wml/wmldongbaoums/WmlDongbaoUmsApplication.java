package com.wml.wmldongbaoums;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/13 - 07 - 13 - 21:04
 * @Description: com.wml.wmldongbaoums
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan("com.wml.wmldongbaoums.mapper")
public class WmlDongbaoUmsApplication {
    //这是一个main方法，是程序的入口：
    public static void main(String[] args) {
        SpringApplication.run(WmlDongbaoUmsApplication.class,args);
    }
}
