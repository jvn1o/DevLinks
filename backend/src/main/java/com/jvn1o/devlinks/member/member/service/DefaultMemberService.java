package com.jvn1o.devlinks.member.member.service;

import com.jvn1o.devlinks.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultMemberService implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public boolean existsByMemberId(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }
}
