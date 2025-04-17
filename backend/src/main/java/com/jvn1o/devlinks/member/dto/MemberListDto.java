package com.jvn1o.devlinks.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberListDto {

    private String userId;
    private String nickname;
    private String name;
    private String email;
    private String profileImgSrc;

}
