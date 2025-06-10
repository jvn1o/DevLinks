package com.jvn1o.devlinks.member.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignupRequestDto {
    private MultipartFile profileImg;
    private String name;
    private String memberId;
    private String memberPwd;
    private String nickname;
    private String email;
    private LocalDate birth;
}
