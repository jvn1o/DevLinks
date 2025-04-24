package com.jvn1o.devlinks.member.link.controller;

import com.jvn1o.devlinks.member.link.dto.MemberLinkListDto;
import com.jvn1o.devlinks.member.link.service.MemberLinkService;
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
public class MemberLinkController {

    private final MemberLinkService memberLinkService;

    @GetMapping
    public ResponseEntity<List<MemberLinkListDto>> getUserLinks(@AuthenticationPrincipal UserPrincipal user) {
        return ResponseEntity.ok(memberLinkService.getUserLinks(user.getId()));
    }
}
