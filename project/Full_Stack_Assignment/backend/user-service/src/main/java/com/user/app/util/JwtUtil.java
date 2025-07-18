package com.user.app.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private final String SECRET = "secret12345";

    public String generateToken(String email, String role, Long id) {
        return Jwts.builder()
            .setSubject(email)
            .claim("role", role)
            .claim("id", id)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 3600000))
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact();
    }
}
