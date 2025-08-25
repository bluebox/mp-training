package com.VIMS.VIMSBackend.Config;

import java.util.Collection;

import javax.crypto.SecretKey;


import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JWTProvider {
    static SecretKey key = Keys.hmacShaKeyFor(JWTConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);
        @SuppressWarnings("deprecation")
        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("UserId", auth.getName())
                .claim( "authorities",roles)
                .signWith(key)
                .compact();
        System.out.println("Token for parsing in JwtProvider: " + jwt);
        return jwt;

    }

    private static String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();
        for(GrantedAuthority authority: authorities) {
            auths.add(authority.getAuthority());
        }
        return String.join(",",auths);
    }


    @SuppressWarnings("deprecation")
    public static String getEmailFromJwtToken(String jwt) {
        jwt = jwt.substring(7); // Assuming "Bearer " is removed from the token
        try {
            //Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
            Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
            String UserId = String.valueOf(claims.get("UserId"));
            System.out.println("UserId extracted from JWT: " + claims);
            return UserId;
        } catch (Exception e) {
            System.err.println("Error extracting UserId from JWT: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
