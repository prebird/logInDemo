package com.example.logindemo.web;

import com.example.logindemo.constant.SessionConst;
import com.example.logindemo.domain.member.Member;
import com.example.logindemo.domain.member.MemberRepository;
import com.example.logindemo.web.argumentResolver.Login;
import com.example.logindemo.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
    public String homeLoginV3ArgumetResolver(Model model, HttpServletRequest request) {

        // 스프링 시큐리티 사용시 코드 -> 세션에서 get 해서 사용안함
        // 세션에서 get 한 Object 를 User or UserDetails로 cast할 수 없음 
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                User user = (User) principal;
                model.addAttribute("user", user);
                return "loginHome";
            }
        }
        return "home";
        // 주석
        // 시큐리티 사용 후 에러난 코드
//        if (user.getUsername() == null) {
//            return "home";
//        }
//        model.addAttribute("user", user);
//        return "loginHome";


        // security 사용 전
//        log.info(member.toString());
//        if (member.getId() == null) {
//            return "home";
//        }
        //model.addAttribute("member", member);
        //return "loginHome";
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
