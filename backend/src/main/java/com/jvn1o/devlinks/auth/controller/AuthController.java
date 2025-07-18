package com.jvn1o.devlinks.auth.controller;

import com.jvn1o.devlinks.auth.dto.LoginRequest;
import com.jvn1o.devlinks.auth.dto.LoginResponse;
import com.jvn1o.devlinks.auth.util.JwtUtil;
import com.jvn1o.devlinks.auth.principal.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getMemberId(), request.getMemberPwd())
        );

        MemberPrincipal userDetails = (MemberPrincipal) authentication.getPrincipal();

        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(LoginResponse.builder()
                .memberId(userDetails.getId())
                .token(token)
                .build());
    }
}
