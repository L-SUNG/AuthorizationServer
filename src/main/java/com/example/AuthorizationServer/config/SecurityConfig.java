package com.example.authorizationserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 로그인 실패 핸들러 의존성 주입
     */
    @Autowired
    AuthenticationFailureHandler customFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().ignoringRequestMatchers(
                    new AntPathRequestMatcher("/h2-console/**"))
                .and()
                    .headers()
                    .addHeaderWriter(new XFrameOptionsHeaderWriter(
                            XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()
                    // 페이지 권한 설정
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/api/v1/**", "/member/login", "/member/signup/**", "/member/logout").permitAll()
                    .antMatchers(HttpMethod.POST, "/h2-console/**", "/member/signup/**", "/api/v1/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    // 로그인
                    .formLogin()
                    .loginPage("/member/login")
                    .defaultSuccessUrl("/", true)
                    .failureHandler(customFailureHandler)
                .and()
                    // 로그아웃
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true);

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}