package com.jvn1o.devlinks.member.member.controller;

import com.jvn1o.devlinks.member.member.dto.MemberSignupRequestDto;
import com.jvn1o.devlinks.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping(value = "/signup", consumes = {"multipart/form-data"})
    public ResponseEntity<?> signup(@ModelAttribute MemberSignupRequestDto request) {
        try {
            memberService.signup(request);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    // ID 중복 체크
    @PostMapping("/idvalid")
    public ResponseEntity<?> checkMemberId(@RequestBody MemberIdRequest request) {
        boolean exists = memberService.existsByMemberId(request.getMemberId());
        if (exists) {
            // 409 Conflict 반환하여 Vue composable에서 처리
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Member ID already exists");
        }
        return ResponseEntity.ok().build();
    }

    // ID 중복 체크 요청 DTO
    public static class MemberIdRequest {
        private String memberId;

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }
    }
}
