package com.wml.caweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/24 - 07 - 24 - 0:34
 * @Description: com.wml.caweb
 * @version: 1.0
 */
@SpringBootApplication
@RestController
public class CaWebApplication {
    //这是一个main方法，是程序的入口：
    public static void main(String[] args) {
        SpringApplication.run(CaWebApplication.class,args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello https";
    }
}
