package com.jvn1o.devlinks.member.member.service;

import com.jvn1o.devlinks.member.member.dto.MemberSignupRequestDto;

public interface MemberService {
    void signup(MemberSignupRequestDto request);

    boolean existsByUserId(String userId);
}
