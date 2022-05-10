package com.jghan.myhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping()
    public String index(){
        return "index";
    }
}


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/home").permitAll() //누구나 접근할수 있는 url
//                .anyRequest().authenticated() //어떤 요청이라도 authenticated 돼야한다.
//                .and()
//                .formLogin() //authenticated안된 상태에서 다른 페이지 가려하면
//                .loginPage("/login") //login페이지로 redirect된다.
//                .permitAll //login 페이지는 누구라도 접근가능
//                .and()
//                .logout()
//                .permitAll();
//    }
