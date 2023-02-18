package com.example.logindemo;

import com.example.logindemo.domain.item.Item;
import com.example.logindemo.domain.item.ItemRepository;
import com.example.logindemo.domain.member.Member;
import com.example.logindemo.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestDataInit {
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 테스트 데이터 추가
     */
    @PostConstruct
    public void init() {

        itemRepository.save(new Item("새우깡", 1000, 20));
        itemRepository.save(new Item("썬칩", 2000, 30));

        memberRepository.save(new Member("admin", "12", "정용규"));
        log.info("test data add");
    }
}
