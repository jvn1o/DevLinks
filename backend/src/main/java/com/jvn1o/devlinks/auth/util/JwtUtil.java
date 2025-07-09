package com.jvn1o.devlinks.auth.util;

import com.jvn1o.devlinks.security.principal.MemberPrincipal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String secret = "19990610";
    private final long expiration = 1000L * 60 * 60; // 1시간

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
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }
}
