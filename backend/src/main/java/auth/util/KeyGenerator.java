package com.m2j2.haruseoul.auth.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {

        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // HS512 알고리즘에 적합한 키 생성
        System.out.println(key.getEncoded());

        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded()); // Base64로 인코딩하여 출력
        System.out.println(base64Key);
    }
}
