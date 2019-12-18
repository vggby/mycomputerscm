package com.mydesign.mycomputerscm;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mydesign.mycomputerscm.mapper")
public class MycomputerscmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycomputerscmApplication.class, args);
    }

}
