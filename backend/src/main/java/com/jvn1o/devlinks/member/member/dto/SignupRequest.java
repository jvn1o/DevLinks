package com.jvn1o.devlinks.member.member.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private MultipartFile profileImg;
    private String name;
    private String memberId;
    private String memberPwd;
    private String nickname;
    private String email;
    private LocalDate birth;
}