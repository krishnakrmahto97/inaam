package io.inaam.main.controller;

import io.inaam.main.dto.AdminDto;
import io.inaam.main.dto.TokenDto;
import io.inaam.main.service.AdminService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final AdminService adminService;
    @Value("${jwt.signing.key:jwtSigningKey}")
    private String signingKey;

    public LoginController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody AdminDto adminDto) {
        if (adminService.authenticate(adminDto)) {
            return new ResponseEntity<>(
                    new TokenDto(getJwt(adminDto)),
                    HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    /**
     * only for testing
     *
     * @param adminDto
     * @return
     */
    @PostMapping("/add-admin")
    public ResponseEntity<TokenDto> addAdmin(@RequestBody AdminDto adminDto) {
        adminService.addAdminUser(adminDto);
        return new ResponseEntity<>(
                new TokenDto(getJwt(adminDto)),
                HttpStatus.OK);
    }

    private String getJwt(AdminDto adminDto) {
        return Jwts
                .builder()
                .setSubject(adminDto.getName())
                .claim("roles", "admin")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }
}
