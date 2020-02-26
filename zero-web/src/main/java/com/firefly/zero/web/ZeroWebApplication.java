package com.firefly.zero.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan(basePackages = {"com.firefly.zero.web.dao"})
@SpringBootApplication
public class ZeroWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZeroWebApplication.class, args);
	}

}
