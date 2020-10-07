package io.inaam.main.service;

import io.inaam.main.dto.UserDto;
import io.inaam.main.entity.UserDetails;
import io.inaam.main.repository.UserAttributeRepository;
import io.inaam.main.repository.UserRepository;
import io.inaam.main.transformer.UserTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService
{
    private final RealmService realmService;
    private final UserRepository userRepository;
    private final UserAttributeRepository userAttributeRepository;
    private final UserTransformer userTransformer;


    @Override
    public UserDetails getUserByNameAndRealmId(String name, String realmId)
    {
        return userRepository.findByNameAndRealmId(name, realmId);
    }

    @Override
    public List<UserDto> getUsers(String realmName)
    {
        List<UserDetails> users = userRepository.findByRealmId(realmService.getRealmId(realmName));
        return userTransformer.toUserDto(users);
    }

    @Override
    public void createUser(String realm, UserDto userDto)
    {
        UserDetails userDetails = userTransformer.toUser(userDto, realmService.getRealmId(realm));
        String userId = userRepository.save(userDetails).getId();

        Optional.ofNullable(userDto.getAttributes())
                .ifPresent(attributes -> attributes.stream()
                                                   .map(attribute -> userTransformer.toUserAttribute(attribute, userId))
                                                   .forEach(userAttributeRepository::save));
    }

    @Override
    public List<String> getUserNames(List<String> userIds)
    {
        List<UserDetails> allByIdIn = userRepository.findAllByIdIn(userIds);
        return userTransformer.toUserNames(allByIdIn);
    }
}
