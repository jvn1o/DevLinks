package com.jvn1o.devlinks.member.link.service;

import com.jvn1o.devlinks.member.link.dto.MemberLinkListDto;

import java.util.List;

public interface MemberLinkService{
    // member 가 본인 작성글 조회
    List<MemberLinkListDto> getMemberLinkList(Long memberId);
}