package com.example.logindemo.web.login;

import com.example.logindemo.domain.member.Member;
import com.example.logindemo.service.LogInService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LogInService logInService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("form") LoginForm form) {

        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("form") LoginForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = logInService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "ID 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //TODO : 로그인 성공

        return "redirect:/";
    }

}
