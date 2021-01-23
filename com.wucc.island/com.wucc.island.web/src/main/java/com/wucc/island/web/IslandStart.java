package com.wucc.island.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-01-15 14:47
 */

@SpringBootApplication
@ComponentScan({"com.wucc"})
@MapperScan({"com.wucc.island.**.repository"})
@EnableCaching

public class IslandStart {

    public static void main(String[] args) throws CloneNotSupportedException {
        SpringApplication app = new SpringApplication(IslandStart.class);
        app.run(args);
    }
}
