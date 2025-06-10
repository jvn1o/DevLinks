package com.jvn1o.devlinks.member.link.service;

import com.jvn1o.devlinks.member.link.dto.MemberLinkListDto;
import com.jvn1o.devlinks.repository.MemberLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMemberLinkService implements MemberLinkService {

    private final MemberLinkRepository memberLinkRepository;

    @Override
    public List<MemberLinkListDto> getMemberLinkList(Long memberId) {
        return memberLinkRepository.findAllByMemberId(memberId);
    }
}
