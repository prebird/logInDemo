package com.example.logindemo.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@ToString
public class Member {
    @NotEmpty
    private Long id;

    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;

    public Member(Long id, String loginId ,String password, String name) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }
    public Member(String loginId ,String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Member empty() {
        return new Member(null, null,null, null);
    }
}
