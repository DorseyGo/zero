/**
 * File: WebMvcConfig
 * Author: DorSey Q F TANG
 * Created: 2020/2/12 21:58
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
