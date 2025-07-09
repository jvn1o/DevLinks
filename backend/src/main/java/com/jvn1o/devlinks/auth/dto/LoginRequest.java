package com.jvn1o.devlinks.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String memberId;
    private String memberPwd;
}