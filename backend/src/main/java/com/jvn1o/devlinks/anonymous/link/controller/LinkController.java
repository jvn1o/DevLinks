package com.jvn1o.devlinks.anonymous.link.controller;

import com.jvn1o.devlinks.anonymous.link.service.LinkService;
import com.jvn1o.devlinks.common.enums.PriceType;
import com.jvn1o.devlinks.user.link.dto.LinkListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/anonymous/links")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @GetMapping("/links")
    public ResponseEntity<List<LinkListDto>> getLinksBySortAndPrice(
            @RequestParam PriceType price,
            @RequestParam String sort
    ) {
        List<LinkListDto> result = linkService.findBySortAndPrice(sort, price);
        return ResponseEntity.ok(result);
    }
}
