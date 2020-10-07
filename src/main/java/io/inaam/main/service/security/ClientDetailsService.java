package io.inaam.main.service.security;

import io.inaam.main.entity.Client;
import io.inaam.main.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.StringJoiner;

@Service
@AllArgsConstructor
public class ClientDetailsService implements UserDetailsService {

    private final ClientService clientService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Client client = clientService.getClient(getRealmName(username), getClientName(username));
        return generateUser(client, getRealmName(username));
    }

    private User generateUser(Client client, String realmName) {
        String username = new StringJoiner("_")
                .add("CLIENT")
                .add(realmName)
                .add(client.getName())
                .toString();
        return new User(username, client.getSecret(), Collections.emptyList());
    }

    private String getRealmName(String username) {
        return username.split("_")[1];
    }

    private String getClientName(String username) {
        return username.split("_")[2];
    }
}
