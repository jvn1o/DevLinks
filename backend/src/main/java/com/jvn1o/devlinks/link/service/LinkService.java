package com.jvn1o.devlinks.link.service;

import com.jvn1o.devlinks.link.dto.LinkListDto;
import com.jvn1o.devlinks.common.enums.PriceType;

import java.util.List;

public interface LinkService {
    // 정렬과 가격에 따른 Link List 조회
    List<LinkListDto> findBySortAndPrice(String slug, String sort, PriceType pricetype);
}
