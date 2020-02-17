/**
 * File: WebSecurityConfig
 * Author: DorSey Q F TANG
 * Created: 2020/2/12 17:19
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.config;

import com.firefly.zero.web.auth.ZeroUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ZeroUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login").loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/index")
                .and().authorizeRequests()
                .antMatchers("/", "/user/login", "/login", "/static/**", "/templates/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();

//        http.rememberMe().tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(3600)
//                .userDetailsService(userDetailsService);

        http.headers().frameOptions().sameOrigin();
        http.logout().logoutSuccessUrl("/login").permitAll();
    }

}
