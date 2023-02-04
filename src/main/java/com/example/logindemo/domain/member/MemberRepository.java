package com.example.logindemo.domain.member;

import com.example.logindemo.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository {
    private static List<Member> members = new ArrayList<>();

    public MemberRepository() {

    }

    public void addMember(Member member) {
        members.add(member);
    }

    public List<Member> getAll() {return members;}

    public Optional<Member> getById(String id) {
        return members.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst();
    }
}
