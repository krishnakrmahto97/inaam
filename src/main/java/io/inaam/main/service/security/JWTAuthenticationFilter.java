package io.inaam.main.service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.inaam.main.dto.JwtTokenDto;
import io.inaam.main.exception.InaamRuntimeException;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            AuthenticationDto authenticationDto = new JsonMapper().readValue(request.getInputStream(),
                    AuthenticationDto.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    authenticationDto.getPrincipal(),
                    authenticationDto.getSecret(),
                    Collections.emptyList());
            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            throw new InaamRuntimeException("Error while trying to read credentials.", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {

        String accessToken = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withClaim("typ", "Bearer")
                .withExpiresAt(Date.from(Instant.now().plusSeconds(SecurityConstants.ACCESS_TOKEN_EXPIRATION_TIME)))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET));

        // TODO: 08/10/2020 Skipped due to time constraint. To be uncommented once refresh token validation has been implemented. 
//        String refreshToken = JWT.create()
//                .withSubject(((User) authResult.getPrincipal()).getUsername())
//                .withClaim("typ", "Refresh")
//                .withExpiresAt(Date.from(Instant.now().plusSeconds(SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME)))
//                .sign(Algorithm.HMAC512(SecurityConstants.SECRET));

        JwtTokenDto jwtTokenDto = new JwtTokenDto(accessToken, null);

        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.getWriter().write(new JsonMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writeValueAsString(jwtTokenDto));
        response.getWriter().flush();
    }

}
