package com.example.authorizationserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    // 페이지 권한 설정
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/api/v1/**", "/member/login", "/member/signin/**", "/member/logout", "/member/mypage").permitAll()
                    .antMatchers(HttpMethod.POST, "/h2-console/**", "/member/loginProc", "/member/signin/**", "/api/v1/**").permitAll()
                    .anyRequest().permitAll()
                .and()
                    // 페이지 액세스 권한 부족시 이동할 로그인화면 설정
                    .formLogin().loginPage("/member/login");

        return http.build();
    }
}