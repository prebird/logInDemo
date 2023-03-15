package com.example.logindemo.service.security;

import com.example.logindemo.domain.member.Member;
import com.example.logindemo.domain.member.MemberRepository;
import com.example.logindemo.service.security.dto.MemberSecurityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {   // call by login

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername : " + username);

        // username, encoded password, authority
        Optional<Member> result = memberRepository.findByLoginId(username);// id로 조회

        if (result.isEmpty()) {
            log.error("아이디가 없습니다");
            throw new UsernameNotFoundException("아이디(username)이 없습니다.");
        }

        Member member = result.get();

        MemberSecurityDto memberSecurityDto = new MemberSecurityDto(member.getLoginId(), member.getPassword(),
                member.getName(), member.getEmail(), false,
                member.getRoleSet()
                        .stream().map(mr -> new SimpleGrantedAuthority("ROLE_"+mr.name()))
                        .collect(Collectors.toList())
                );

        log.info("memberSecurityDto");
        log.info(String.valueOf(memberSecurityDto));

        return memberSecurityDto;
    }
}
