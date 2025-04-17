package com.jvn1o.devlinks.user.link.controller;

import com.jvn1o.devlinks.user.link.dto.LinkListDto;
import com.jvn1o.devlinks.user.link.service.LinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController("userLinkController")
@RequestMapping("/user/links")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @GetMapping
    public ResponseEntity<List<LinkListDto>> getUserLinks(@AuthenticationPrincipal UserPrincipal user) {
        return ResponseEntity.ok(linkService.getUserLinks(user.getId()));
    }
}
