package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/14 - 07 - 14 - 7:58
 * @Description: com
 * @version: 1.0
 */
//扫描portal包
@SpringBootApplication(scanBasePackages = {"com.wml"})
//扫描mapper
@MapperScan("com.wml.dongbao.ums.mapper")
public class WmlDongbaoPortalWebApplication {
    //这是一个main方法，是程序的入口：
    public static void main(String[] args) {
        SpringApplication.run(WmlDongbaoPortalWebApplication.class,args);
    }
}
