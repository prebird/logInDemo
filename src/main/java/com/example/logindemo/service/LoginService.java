package com.example.logindemo.service;

import com.example.logindemo.domain.member.Member;
import com.example.logindemo.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
          return memberRepository.findByLoginId(loginId)
                  .filter(m -> m.getPassword().equals(password))
                  .orElse(null);
    }

    public void updateAutoLoginUUID(Long id, String uuid) {
        memberRepository.setAutoLoginId(id, uuid);
    }
}
