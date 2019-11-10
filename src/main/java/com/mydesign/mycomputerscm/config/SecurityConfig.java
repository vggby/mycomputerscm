package com.mydesign.mycomputerscm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder createPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        //super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/css/**","/js/**").permitAll()
                .and()
                .formLogin().loginPage("/user/login").loginProcessingUrl("/user/login/form").failureUrl("/login-error").permitAll()
                .and()
                .csrf().disable()
        ;//表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面



    }

}
