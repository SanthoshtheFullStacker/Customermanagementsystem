package com.example.Customermanagement.auth.Serviceimpl;

import com.example.Customermanagement.auth.Model.Usermodel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTserviceimpl {

    Map<String, String> claims = new HashMap<>();

    private final String SECRET_KEY = "eb2b900a6c07b7a0f62a0ae4c5de9d02aa0d7d3143db0860e3b8f9adbdd01316c70096cbd60dc5ee214d1eb02ec9f6bf1616535c908635885d743a423e6f1932";

    public String generateToken(Usermodel usermodel) {
        claims.put("username", usermodel.getUsername());
        claims.put("password", usermodel.getPassword());
        claims.put("role", String.valueOf(usermodel.getRole()));

        return Jwts.builder()
                .claims().add(claims)
                .subject(usermodel.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60*60*1000))
                .and().signWith(getKey()).compact();
    }

    public SecretKey getKey(){
        byte[] bytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    public Claims extractClaims(String token){
        return Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
