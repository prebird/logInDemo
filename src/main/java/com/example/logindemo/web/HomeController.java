package com.example.logindemo.web;

import com.example.logindemo.constant.SessionConst;
import com.example.logindemo.domain.member.Member;
import com.example.logindemo.domain.member.MemberRepository;
import com.example.logindemo.web.argumentResolver.Login;
import com.example.logindemo.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final SessionManager sessionManager;

    @GetMapping("/")
    public String homeLoginV3ArgumetResolver(@Login Member member, Model model, HttpServletRequest request) {
        log.info("spring-security는 로그인 쿠키 저장에 어떤 세션 ID를 쓸까");

        HttpSession session = request.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attr = attributeNames.nextElement();
            log.info(attr);
        }
        Object sessionObject = session.getAttribute("SPRING_SECURITY_CONTEXT");
        log.info(sessionObject.toString());
        
        log.info(member.toString());
        if (member.getId() == null) {
            return "home";
        }
        log.info("loginHome");
        model.addAttribute("member", member);
        return "loginHome";
    }

//    @GetMapping("/")
//    public String homeLogin(HttpServletRequest request, Model model) {
//
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return "home";
//        }
//
//        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//
//        if (member == null) {
//            return "home";
//        }
//
//        model.addAttribute("member", member);
//        return "loginHome";
//    }



}
