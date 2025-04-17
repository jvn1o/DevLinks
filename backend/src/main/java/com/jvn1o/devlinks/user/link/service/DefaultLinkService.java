package com.jvn1o.devlinks.user.link.service;

import com.jvn1o.devlinks.common.enums.PriceType;
import com.jvn1o.devlinks.user.link.dto.LinkListDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class DefaultLinkService implements LinkService {

    LinkService linkService;

    @Override
    public List<LinkListDto> getList(Long userId) {
        return List.of();
    }
}
