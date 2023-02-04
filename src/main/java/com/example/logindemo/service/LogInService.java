package com.example.logindemo.service;

import com.example.logindemo.domain.member.Member;
import com.example.logindemo.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogInService {
    private final MemberRepository memberRepository;

    /**
     * @return null 이면 실패
     */
    public Member login(String loginId, String password) {

        return memberRepository.getById(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
