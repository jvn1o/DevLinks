package com.jvn1o.devlinks.member.link.service;

import com.jvn1o.devlinks.member.link.dto.MemberLinkCreateDto;
import com.jvn1o.devlinks.member.link.dto.LinkListDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LinkService {
    // member 가 본인 작성글 조회
    List<LinkListDto> getMemberLinkList(Long memberId);

    // 링크 생성
    void createLink(MemberLinkCreateDto dto, MultipartFile imageFile, Long memberId) throws IOException;

}