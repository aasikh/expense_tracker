package com.example.expene_tracker.util;
import com.example.expene_tracker.service.UserService;
import com.example.expene_tracker.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
@Component
public class JwtUtil {
    private String secret = "mysecretkeymysecretkeymysecretkeymysecretkeymysecretkey";
    private long expiration  = 1000 * 60 * 60;

public String genrateToken(User user){
    return Jwts.builder()
            .setSubject(String.valueOf(user.getId()))
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
            .compact();
     }
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
