package com.jvn1o.devlinks.member.member.controller;

import com.jvn1o.devlinks.member.member.dto.SignupRequest;
import com.jvn1o.devlinks.member.member.service.DefaultSignupService;
import com.jvn1o.devlinks.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final DefaultSignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        signupService.signup(request);
        return ResponseEntity.ok("회원가입 완료");
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
