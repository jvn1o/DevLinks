package com.jvn1o.devlinks.anonymous.link.service;

import com.jvn1o.devlinks.common.enums.PriceType;
import com.jvn1o.devlinks.repository.LinkRepository;
import com.jvn1o.devlinks.user.link.dto.LinkListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultLinkService implements LinkService {

    private final LinkRepository linkRepository;

    @Override
    public List<LinkListDto> findBySortAndPrice(String sort, PriceType price) {
        return List.of();
    }
}