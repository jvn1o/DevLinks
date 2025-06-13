package com.jvn1o.devlinks.member.link.controller;

import com.jvn1o.devlinks.member.link.dto.MemberLinkCreateDto;
import com.jvn1o.devlinks.member.link.dto.MemberLinkListDto;
import com.jvn1o.devlinks.member.link.service.MemberLinkService;
import com.jvn1o.devlinks.security.principal.MemberPrincipal;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController("memberLinkController")
@RequestMapping("/member/link")
@RequiredArgsConstructor
public class MemberLinkController {

    private final MemberLinkService memberLinkService;

    @GetMapping
    public ResponseEntity<List<MemberLinkListDto>> getMemberLinkList(@AuthenticationPrincipal MemberPrincipal member) {
        return ResponseEntity.ok(memberLinkService.getMemberLinkList(member.getId()));
    }

//    @PostMapping("/create")
//    public ResponseEntity<?> createLink(
//            @ModelAttribute MemberLinkCreateDto memberLinkCreateDto,
//            @RequestParam(value = "image") MultipartFile imageFile
//    ) {
//        return ResponseEntity.ok().build();
//    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createLink(
            @RequestPart("data") MemberLinkCreateDto dto,
            @RequestPart(value = "image", required = false) MultipartFile imageFile,
            @AuthenticationPrincipal MemberPrincipal member
    ) throws IOException {
        memberLinkService.createLink(dto, imageFile, member.getId());
        return ResponseEntity.ok().build();
    }
}
