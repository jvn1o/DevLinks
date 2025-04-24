package com.jvn1o.devlinks.member.link.service;

import com.jvn1o.devlinks.member.link.dto.MemberLinkListDto;
import com.jvn1o.devlinks.repository.MemberLinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMemberLinkService implements MemberLinkService {

    MemberLinkRepository memberLinkRepository;

    @Override
    public List<MemberLinkListDto> getList(Long userId) {
        return List.of();
    }
}
