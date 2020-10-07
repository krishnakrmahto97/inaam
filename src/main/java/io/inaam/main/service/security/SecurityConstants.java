package io.inaam.main.service.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityConstants {
    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 900;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String UNAUTHORIZED_MESSAGE = "Unauthorized";
}
