package io.inaam.main.service.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.StringJoiner;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {
    private String name;
    private String realmName;
    private String secret;
    private String refreshToken;

    public AuthenticationType getType() {
        return realmName == null ? AuthenticationType.ROLE_ADMIN : AuthenticationType.ROLE_CLIENT;
    }

    public String getPrincipal() {
        StringJoiner principal = new StringJoiner(":").add(getType().name());
        if (getType() == AuthenticationType.ROLE_CLIENT) {
            principal.add(realmName);
        }
        return principal.add(name).toString();
    }

    public AuthenticationDto(String principal) {
        String[] attributes = principal.split(":");
        AuthenticationType authenticationType = AuthenticationType.valueOf(attributes[0]);
        if (authenticationType == AuthenticationType.ROLE_ADMIN) {
            this.name = attributes[1];
        } else if (authenticationType == AuthenticationType.ROLE_CLIENT) {
            this.realmName = attributes[1];
            this.name = attributes[2];
        }
    }
}
