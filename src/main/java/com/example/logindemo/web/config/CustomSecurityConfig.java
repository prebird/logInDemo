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
        // 기존의 로그인 처리를 타지 않고, POST 로그인 역시 security 내부에서 처리하게 된다.
        // 로그아웃도 시큐리티에서 처리해준다. -> 로그아웃 후 로그인 화면을 호출해 준다.
        // we don't need to make POST-login, POST-logout
        
        http.csrf().disable(); // csrf 토근 확인 비활성화

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
