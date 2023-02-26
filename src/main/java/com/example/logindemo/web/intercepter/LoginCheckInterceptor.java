package com.example.logindemo.web.intercepter;

import com.example.logindemo.constant.CookieConst;
import com.example.logindemo.constant.SessionConst;
import com.example.logindemo.domain.member.Member;
import com.example.logindemo.service.LoginService;
import com.example.logindemo.service.MemberService;
import com.example.logindemo.web.commonUtil.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("로그인 인증 체크 {}", requestURI);


        HttpSession session = request.getSession();
        // 세션 체크 2023.02.26 자동로그인 추가로 변경됨
        if (session != null || session.getAttribute(SessionConst.LOGIN_MEMBER) != null) {
            return true;
        }

        // 쿠키에 자동로그인 체크
        Cookie autoLoginCookie = CookieUtils.findCookie(request, CookieConst.AUTO_LOGIN);
        if (autoLoginCookie == null) {
            handleUnAuthorized(response, requestURI);
            return false;
        }
        // 자동 로그인 체크 (세션에 없고 쿠키에는 있는 경우)
        Member member = memberService.findByAutoLoginId(autoLoginCookie.getValue());
        if (member == null) {
            handleUnAuthorized(response, requestURI);
            return false;
        }

        // 회원정보를 세션에 추가
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);

        return true;
    }

    private static void handleUnAuthorized(HttpServletResponse response, String requestURI) throws IOException {
        log.info("미인증 사용자 요청");
        // 로그인 페이지로 redirect
        response.sendRedirect("/login?redirectURL=" + requestURI);
    }
}
