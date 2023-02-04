package com.example.logindemo.domain.member;

import com.example.logindemo.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRepository {
    private static Map<String, Member> members = new HashMap<>();

    public MemberRepository() {

    }

    public void addMember(Member member) {
        members.put(member.getId(), member);
    }

    public Map<String, Member> getAll() {return members;}

    public Member getById(String id) {
        return members.get(id);
    }

    public Boolean isMemberValid(String id, String password) {
        Member member = members.get(id);
        if(member.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
