package com.hl.bootlearnmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan("com.hl")
@SpringBootApplication

@MapperScan(basePackages = "com.hl.bootlearnmall.Mapper")
@EnableSwagger2
@EnableCaching
public class BootlearnmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootlearnmallApplication.class, args);
    }

}
