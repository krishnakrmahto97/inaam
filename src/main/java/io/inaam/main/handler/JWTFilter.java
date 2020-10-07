package io.inaam.main.handler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class JWTFilter implements Filter {

    @Value("${jwt.signing.key:jwtSigningKey}")
    private String signingKey;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final String authorization = request.getHeader("authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
            return;
        }
        try {
            final Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(authorization.substring(7))
                    .getBody();
            request.setAttribute("claims", claims);
        } catch (final SignatureException ex) {
            ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
            return;
        }
        chain.doFilter(req, res);
    }
}