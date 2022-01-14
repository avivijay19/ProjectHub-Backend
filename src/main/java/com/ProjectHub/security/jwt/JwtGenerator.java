package com.ProjectHub.security.jwt;

import com.ProjectHub.model.security.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {

    public String generate(JwtUser jwtUser) {
        Claims claims = Jwts.claims().setSubject(jwtUser.getUsername());
        claims.put("role", jwtUser.getRole());

        return Jwts.builder().
                setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512, "tolusecret")
                .setHeaderParam("Type", "JWT")
                .compact();
    }
}
