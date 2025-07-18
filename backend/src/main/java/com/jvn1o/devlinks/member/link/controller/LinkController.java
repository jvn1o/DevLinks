package com.jvn1o.devlinks.member.link.controller;

import com.jvn1o.devlinks.member.link.dto.MemberLinkCreateDto;
import com.jvn1o.devlinks.member.link.dto.LinkListDto;
import com.jvn1o.devlinks.member.link.service.LinkService;
import com.jvn1o.devlinks.auth.principal.MemberPrincipal;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController("MemberLinkController")
@RequestMapping("/member")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    // front 의 member/my/links 와 매핑
    @GetMapping("/my/links")
    public ResponseEntity<List<LinkListDto>> getMemberLinkList(@AuthenticationPrincipal MemberPrincipal member) {
        return ResponseEntity.ok(linkService.getMemberLinkList(member.getId()));
    }

    @PostMapping(value = "/link/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createLink(
            @RequestPart("data") MemberLinkCreateDto dto,
            @RequestPart(value = "image", required = false) MultipartFile imageFile,
            @AuthenticationPrincipal MemberPrincipal member
    ) throws IOException {
        linkService.createLink(dto, imageFile, member.getId());
        return ResponseEntity.ok().build();
    }
}
