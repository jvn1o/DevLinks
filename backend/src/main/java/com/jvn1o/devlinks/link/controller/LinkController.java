package com.jvn1o.devlinks.link.controller;

import com.jvn1o.devlinks.link.dto.LinkListDto;
import com.jvn1o.devlinks.link.service.LinkService;
import com.jvn1o.devlinks.common.enums.PriceType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/links")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    // 가격 종류는 필터링 요청이 없을때는 모두 반환하고, 정렬은 최신순이 기준으로 반환
    @GetMapping("/category/{slug}")
    public ResponseEntity<List<LinkListDto>> getLinksBySortAndPrice(
            @PathVariable("slug") String slug,
            @RequestParam(required = false) PriceType pricetype,  // optional
            @RequestParam String sort
    ) {
        List<LinkListDto> result = linkService.findBySortAndPrice(slug, sort, pricetype);
        return ResponseEntity.ok(result);
    }
}
