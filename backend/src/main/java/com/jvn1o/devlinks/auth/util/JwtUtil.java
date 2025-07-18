package com.jvn1o.devlinks.auth.util;

import com.jvn1o.devlinks.auth.principal.MemberPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key secretKey;
    private final long expiration; // 밀리초 단위

    // application.yml 또는 application.properties 에서 값 주입
    public JwtUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration}") long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)); // Base64 디코딩
        this.expiration = expiration;
    }

    // JWT 생성
    public String generateToken(MemberPrincipal userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("id", userDetails.getId())
                .claim("name", userDetails.getName())
                .claim("email", userDetails.getEmail())
                .claim("nickname", userDetails.getNickname())
                .claim("profileImgSrc", userDetails.getProfileImgSrc())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // 토큰에서 사용자 ID 추출
    public Long extractUserId(String token) {
        return parseClaims(token).get("id", Long.class);
    }

    // 토큰에서 사용자 이름 추출
    public String extractName(String token) {
        return parseClaims(token).get("name", String.class);
    }

    //  클레임 파싱
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
