package com.example.logindemo.service;

import com.example.logindemo.domain.member.Member;
import com.example.logindemo.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findByAutoLoginId(String uuid) {
        return memberRepository.findByAutoLoginID(uuid).orElse(null);
    }
}
