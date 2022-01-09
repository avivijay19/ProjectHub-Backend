package com.ProjectHub.security.jwt;

import com.ProjectHub.model.security.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private final String secret = "tolusecret";

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();
            jwtUser.setUsername(body.getSubject());
            jwtUser.setRole((String) body.get("password"));
            jwtUser.setRole((String) body.get("role"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jwtUser;
    }
}
