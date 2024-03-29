package com.example.logindemo.web.login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class LoginForm {

    @NotEmpty
    //private String loginId;
    private String username;
    @NotEmpty
    private String password;
    private Boolean autoLogin;
}
