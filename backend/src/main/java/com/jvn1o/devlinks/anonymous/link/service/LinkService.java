package com.jvn1o.devlinks.anonymous.link.service;

import com.jvn1o.devlinks.common.enums.PriceType;
import com.jvn1o.devlinks.user.link.dto.LinkListDto;

import java.util.List;

public interface LinkService {
    // 정렬과 가격에 따른 Link List 조회
    List<LinkListDto> findBySortAndPrice(String sort, PriceType price);
}
