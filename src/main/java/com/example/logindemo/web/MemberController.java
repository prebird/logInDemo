package com.example.logindemo.web;

import com.example.logindemo.domain.member.Member;
import com.example.logindemo.domain.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("member", Member.empty());
        return "member/addForm";
    }

    @PostMapping("/add")
    public String join(@ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/addForm";
        }
        memberRepository.addMember(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    @ResponseBody
    public Map<String, Member> members() {
        Map<String, Member> all = memberRepository.getAll();
        log.info(all.toString());
        return all;
    }
}
