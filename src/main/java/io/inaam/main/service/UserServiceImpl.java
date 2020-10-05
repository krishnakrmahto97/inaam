package io.inaam.main.service;

import io.inaam.main.dto.UserDto;
import io.inaam.main.entity.Realm;
import io.inaam.main.entity.UserDetails;
import io.inaam.main.repository.UserRepository;
import io.inaam.main.transformer.UserTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService
{
    private final RealmService realmService;
    private final UserRepository userRepository;
    private final UserTransformer userTransformer;


    @Override
    public List<UserDto> getUsers(String realmName)
    {
        Realm realm = realmService.getRealm(realmName);
        String id = realm.getId();

        List<UserDetails> users = userRepository.findByRealmId(id);


        return null;
    }

    @Override
    public UserDto createUser(UserDto userDto)
    {
        return null;
    }
}
