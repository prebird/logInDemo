package com.example.logindemo.domain.member;

import com.example.logindemo.domain.memberRole.MemberRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void 회원가입_암호인코딩() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            Member member = new Member("member" + i, passwordEncoder.encode("111"), "사람");

            member.addRole(MemberRole.USER);

            if (i >= 90) {
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        });

        List<Member> all = memberRepository.findAll();
        for (Member m: all) {
            System.out.println(m);
        }
    }


}
