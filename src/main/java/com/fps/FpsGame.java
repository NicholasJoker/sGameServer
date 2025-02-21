package com.fps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableScheduling
@SpringBootApplication
@MapperScan("com.fps.dao")
@ServletComponentScan(basePackages = {"com.fps.filter"})
public class FpsGame {
	public static void main(String[] args) {
		SpringApplication.run(FpsGame.class, args);
	}
}
