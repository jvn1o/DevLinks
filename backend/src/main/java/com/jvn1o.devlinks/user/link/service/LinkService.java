package com.jvn1o.devlinks.user.service;

public interface LinkService {
    // user가 본인 작성글 조회
    List<LinkFilterListDto> getList(Long userId);
}