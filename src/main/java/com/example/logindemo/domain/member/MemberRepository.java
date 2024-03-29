package com.example.logindemo.domain.member;

import com.example.logindemo.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member= {}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return store.values().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .filter(m -> m.getSocial().equals(false))   // 소셜 로그인 안된 인원만
                .findFirst();
    }

    public void setAutoLoginId(Long id, String autoLoginId) {
        Member member = store.get(id);
        member.setAutoLoginID(autoLoginId);
    }

    public Optional<Member> findByAutoLoginID(String uuid) {
        return store.values().stream()
                .filter(m -> m.getAutoLoginID().equals(uuid))
                .findAny();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
