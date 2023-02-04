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
    private String id;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;

    public Member(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public static Member empty() {
        return new Member(null, null, null);
    }
}
