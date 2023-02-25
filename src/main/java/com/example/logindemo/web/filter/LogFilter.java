package com.example.logindemo.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("요청이 올때 마다 필터가 작동합니다");

        // 다운캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        //HttpServletResponse HttpResponse = (HttpServletResponse)response;

        // 다음 필터 or 타겟 호출
        chain.doFilter(request, response);
    }
}
