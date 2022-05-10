package com.jghan.myhome.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //application.properties의 dataSource가져옴
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/account/register", "/css/**").permitAll() //누구나 접근할수 있는 url
                    .anyRequest().authenticated() //어떤 요청이라도 authenticated 돼야한다.
                    .and()
                .formLogin() //authenticated안된 상태에서 다른 페이지 가려하면
                    .loginPage("/account/login") //login페이지로 redirect된다.
                    .permitAll() //login 페이지는 누구라도 접근가능
                    .and()
                .logout()
                    .permitAll();
    }

    //테이블에 쿼리 날림
    //스프링 datasource 가져와서 알아서 내부에서 인증처리
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder()) //알아서 비번 암호화
                .usersByUsernameQuery ("select username,password,enabled "  //인증처리
                        + "from user "
                        + "where username = ?")
                .authoritiesByUsernameQuery("select u.username, r.name " //권한처리
                        + "from user_role ur inner join user u on ur.user_id =u.id "
                        +  "inner join role r on ur.role_id = r.id "
                        + "where u.username = ?");
    }

//    Authentication :로그인(인증관련)
//    Authroization: 권한관련

    @Bean
    public static PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


}