package io.inaam.main.service.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityConstants {
    public static final String SECRET = "SECRET_KEY";
    public static final long ACCESS_TOKEN_EXPIRATION_TIME = 86_400;
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 1_728_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
