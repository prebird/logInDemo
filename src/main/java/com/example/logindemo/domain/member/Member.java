package com.example.logindemo.domain.member;

import com.example.logindemo.domain.memberRole.MemberRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Getter @Setter
@ToString
public class Member {
    private Long id;
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;

    private String email;

    private Set<MemberRole> roleSet;

    private String social;

    private String autoLoginID;

    public Member() {}

    public Member(Long id, String loginId ,String password, String name) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

    public Member(Long id, String loginId ,String password, String name, String email, String social) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.social = social;
    }

    public Member(String loginId ,String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addRoleSet(MemberRole memberRole) {
        this.roleSet.add(memberRole);
    }

    public static Member empty() {
        return new Member(null, null,null, null);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", autoLoginID='" + autoLoginID + '\'' +
                '}';
    }
}
