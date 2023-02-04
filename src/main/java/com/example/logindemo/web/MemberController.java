package com.example.logindemo.web;

import com.example.logindemo.domain.member.Member;
import com.example.logindemo.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("member", new Member());
        return "member/addForm";
    }

    @PostMapping("/add")
    public String add(Model model) {
        return "redirect:/";
    }
}
