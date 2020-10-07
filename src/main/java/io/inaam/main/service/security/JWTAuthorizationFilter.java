package io.inaam.main.service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        Optional.ofNullable(request.getHeader(SecurityConstants.HEADER_STRING))
                .ifPresent(authorizationHeader -> {
                    UsernamePasswordAuthenticationToken authentication = getAuthentication(authorizationHeader);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(@NonNull String authorizationHeader) {

        return Optional.ofNullable(JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
                .build()
                .verify(authorizationHeader.replace(SecurityConstants.TOKEN_PREFIX, "")).getSubject())
                .map(user -> new UsernamePasswordAuthenticationToken(user, null))
                .orElse(null);
    }
}
