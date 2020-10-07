package io.inaam.main.service.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientAuthenticationRequest {
    private String name;
    private String secret;
    private String realmName;
}
