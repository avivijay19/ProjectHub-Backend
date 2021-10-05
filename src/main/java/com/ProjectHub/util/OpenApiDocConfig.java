package com.ProjectHub.util;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

import static com.ProjectHub.util.Constants.BEARER_AUTH;

/**
 * Created by Avinash Vijayvargiya on 30-09-2021.
 */
@Configuration
@SecurityScheme(name = BEARER_AUTH, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class OpenApiDocConfig {

}
