package com.example.logindemo.web.login;

import com.example.logindemo.constant.SessionConst;
import com.example.logindemo.domain.member.Member;
import com.example.logindemo.service.LoginService;
import com.example.logindemo.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {


    private final LoginService logInService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm form,
                        BindingResult bindingResult,
                        HttpServletRequest request, HttpServletResponse response,
                        @RequestParam(defaultValue = "/") String redirectURL) { // 없으면 /,  파라메터 명과 동일해야함
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = logInService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "ID 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        // 세션 관리자를 통해 회원데이터를 보관함
        //sessionManager.createSession(loginMember, response);

        
        // 쿠키로만 로그인 처리
//        Cookie cookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
//        response.addCookie(cookie);

        // 스프링 제공 세션 사용
        // 세션이 있으면 재사용, 없으면 생성
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        // 자동 로그인 처리
        boolean isAutoLogin = form.getAutoLogin();

        if (isAutoLogin) {
            String uuid = UUID.randomUUID().toString();
            logInService.updateAutoLoginUUID(loginMember.getId(), uuid); // DB저장

            // 쿠키 저장
            Cookie autoLoginCookie = new Cookie(SessionConst.AUTO_LOGIN, uuid);
            autoLoginCookie.setMaxAge(60*60*24*7); // 일주일
            autoLoginCookie.setPath("/");
            response.addCookie(autoLoginCookie);
        }

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logOut(HttpServletRequest request, HttpServletResponse response) {

        // 세션 없어도 새 세션 만들지 않기
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
