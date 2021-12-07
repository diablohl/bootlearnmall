package com.hl.bootlearnmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.hl")
@SpringBootApplication
@MapperScan(basePackages = "com.hl.bootlearnmall.Mapper")
public class BootlearnmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootlearnmallApplication.class, args);
    }

}
