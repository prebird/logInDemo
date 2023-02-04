package com.example.logindemo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class Member {
    private String id;
    private String password;
    private String name;

    public Member(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
