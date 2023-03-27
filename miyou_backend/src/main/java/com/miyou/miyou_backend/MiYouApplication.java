package com.miyou.miyou_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author LINGLAN
 */
@SpringBootApplication
@MapperScan("com.miyou.miyou_backend.mapper")
public class MiYouApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiYouApplication.class, args);
    }

}
