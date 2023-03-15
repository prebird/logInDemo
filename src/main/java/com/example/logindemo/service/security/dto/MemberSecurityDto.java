package com.example.logindemo.service.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// UserDetails 타입을 만족하도록 구성
@ToString
@Getter
@Setter
public class MemberSecurityDto extends User {

    private String loginId;
    private String password;
    private String name;
    private String email;
    private Boolean social;

    public MemberSecurityDto(String username, String password, String name,
                             String email, Boolean social,
                             Collection<? extends GrantedAuthority> authorities) {

        super(username, password, authorities);

        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.social = social;
    }
}
