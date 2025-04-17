package com.jvn1o.devlinks.user.link.service;

import com.jvn1o.devlinks.user.link.dto.LinkListDto;

import java.util.List;

public interface LinkService {
    // user 가 본인 작성글 조회
    List<LinkListDto> getList(Long userId);
}