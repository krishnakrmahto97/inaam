package io.inaam.main.service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
import java.util.Date;
import java.util.StringJoiner;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/client/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            ClientAuthenticationRequest authenticationRequest = new JsonMapper()
                    .readValue(request.getInputStream(), ClientAuthenticationRequest.class);
            return authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(generatePrincipal(authenticationRequest.getName(),
                            authenticationRequest.getRealmName()),
                            authenticationRequest.getSecret()));
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
                .withExpiresAt(Date.from(Instant.now().plusSeconds(SecurityConstants.EXPIRATION_TIME)))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET));

        /*
         * Skipping refresh-token for now.
         */

//        String refreshToken = JWT.create()
//                .withSubject(((User) authResult.getPrincipal()).getUsername())
//                .withClaim("typ", "Refresh")
//                .withExpiresAt(Date.from(Instant.now().plusSeconds(SecurityConstants.EXPIRATION_TIME)))
//                .sign(Algorithm.HMAC512(SecurityConstants.SECRET));

        JwtTokenDto jwtTokenDto = new JwtTokenDto(accessToken);
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.getWriter().write(new JsonMapper().writeValueAsString(jwtTokenDto));
        response.getWriter().flush();
    }

    private String generatePrincipal(String clientName, String realmName) {
        return new StringJoiner("_")
                .add("CLIENT")
                .add(realmName)
                .add(clientName)
                .toString();
    }
}
