package com.example.neighborgethering;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.example.neighborgethering.controller")

@MapperScan("generator.mapper")
public class NeighborGetheringApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeighborGetheringApplication.class, args);
    }

}
