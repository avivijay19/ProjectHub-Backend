package com.ProjectHub.security;

import com.ProjectHub.model.security.JwtUser;
import com.ProjectHub.security.jwt.JwtGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class TokenController {

    private final JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping()
    public String generate(@RequestBody final JwtUser jwtUser) {
        return jwtGenerator.generate(jwtUser);
    }
}
