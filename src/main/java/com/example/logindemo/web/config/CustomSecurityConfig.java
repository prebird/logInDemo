package com.example.logindemo.web.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 특정 경로에 시큐리티 적용
public class CustomSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("--------------- configure ---------------");

        http.formLogin().loginPage("/login");   // 커스텀 로그인 페이지

        return http.build();
    }

    // ignore filter on static files
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        log.info("-------------web configure--------------");

        return (web) -> web.ignoring().requestMatchers(PathRequest.
                                                        toStaticResources().atCommonLocations());
    }
}
