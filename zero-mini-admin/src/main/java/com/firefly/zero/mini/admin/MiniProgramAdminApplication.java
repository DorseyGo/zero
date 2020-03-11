/**
 * File: MiniProgramAdminApplication
 * Author: DORSEy Q F TANG
 * Created: 2020/3/11
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.firefly.zero.mini.admin.repository"})
@SpringBootApplication
public class MiniProgramAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniProgramAdminApplication.class, args);
    }
}
