package io.inaam.main.service.security;

import io.inaam.main.entity.Admin;
import io.inaam.main.entity.Client;
import io.inaam.main.repository.AdminRepository;
import io.inaam.main.repository.ClientRepository;
import io.inaam.main.service.RealmService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;

    private final ClientRepository clientRepository;

    private final RealmService realmService;

    @Override
    public UserDetails loadUserByUsername(String username) {
       AuthenticationDto authenticationDto = new AuthenticationDto(username);
       AuthenticationType authenticationType = authenticationDto.getType();

       if (authenticationType == AuthenticationType.ROLE_ADMIN) {
           Admin admin = adminRepository.findByName(authenticationDto.getName());
           authenticationDto.setSecret(admin.getSecret());
       } else if (authenticationType == AuthenticationType.ROLE_CLIENT) {
           Client client = clientRepository.findByNameAndRealmId(authenticationDto.getName(),
                   realmService.getRealmId(authenticationDto.getRealmName()));
           authenticationDto.setSecret(client.getSecret());
       }
       return new User(authenticationDto.getPrincipal(),
               authenticationDto.getSecret(),
               Collections.singletonList(new SimpleGrantedAuthority(authenticationType.name())));
    }
}
