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

    @GetMapping("/category/{slug}")
    public ResponseEntity<List<LinkListDto>> getLinksBySortAndPrice(
            @PathVariable("slug") String slug,
            @RequestParam PriceType pricetype,
            @RequestParam String sort
    ) {
        List<LinkListDto> result = linkService.findBySortAndPrice(slug, sort, pricetype);
        return ResponseEntity.ok(result);
    }
}
