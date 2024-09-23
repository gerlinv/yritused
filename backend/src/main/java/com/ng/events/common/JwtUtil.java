package com.ng.events.common;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    // Currenly unused
    @Value("${jwt.expiration}")
    private Long jwtExpirationMs;

    public Claims extractClaims(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String generateToken(String email, String role) {
        Map<String, Object> claims = Map.of(
            "email", email,
            "role", role
        );

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Long getExpirationTime() {
        return jwtExpirationMs;
    }

}
