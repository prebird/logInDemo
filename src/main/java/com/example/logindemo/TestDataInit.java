package com.example.logindemo;

import com.example.logindemo.domain.item.Item;
import com.example.logindemo.domain.item.ItemRepository;
import com.example.logindemo.domain.member.Member;
import com.example.logindemo.domain.member.MemberRepository;
import com.example.logindemo.domain.memberRole.MemberRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestDataInit {
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 테스트 데이터 추가
     */
    @PostConstruct
    public void init() {

        itemRepository.save(new Item("새우깡", 1000, 20));
        itemRepository.save(new Item("썬칩", 2000, 30));
        itemRepository.save(new Item("감자칩", 1000, 20));
        itemRepository.save(new Item("빼빼로", 2000, 30));

        Member member1 = memberRepository.save(new Member("admin", passwordEncoder.encode("12"), "jeong"));
        //memberRepository.setAutoLoginId(member1.getId(), "84c247d3-ae86-4eee-95ab-bcce4567e598");
        member1.addRole(MemberRole.ADMIN);
        Member member2 = new Member("user1", passwordEncoder.encode("123"), "koo");
        member2.addRole(MemberRole.USER);

        memberRepository.save(member1);
        memberRepository.save(member2);
        log.info("test data add");
    }
}
