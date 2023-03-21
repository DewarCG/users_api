package com.nisum.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Claims claims) {
        return Jwts.builder().setClaims(claims).
                signWith(SignatureAlgorithm.HS256, secret).
                compact();
    }

    public Claims getTokenInfo(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
