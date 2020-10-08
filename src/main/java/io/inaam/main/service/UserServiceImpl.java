package io.inaam.main.service;

import io.inaam.main.dto.AttributeDto;
import io.inaam.main.dto.UserDto;
import io.inaam.main.entity.Status;
import io.inaam.main.entity.UserDetails;
import io.inaam.main.exception.UserException;
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
    public String getUserId(String name, String realmId)
    {
        return getUserEntity(name, realmId).getId();
    }

    @Override
    public UserDto getUser(String name, String realmName)
    {
        String realmId = realmService.getRealmId(realmName);
        return userTransformer.toUserDto(getUserEntity(name, realmId));
    }

    private UserDetails getUserEntity(String name, String realmId)
    {
        return Optional.ofNullable(userRepository.findByNameAndRealmId(name, realmId))
                       .orElseThrow(() -> new UserException(UserException.NO_ACTIVE_USER));
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
    public List<String> getUsernames(List<String> userIds)
    {
        List<UserDetails> allByIdIn = userRepository.findAllByIdIn(userIds);
        return userTransformer.toUserNames(allByIdIn);
    }

    @Override
    public void addAttribute(String realm, String userName, AttributeDto attribute)
    {
        String userId = getUserId(userName, realmService.getRealmId(realm));
        userAttributeRepository.save(userTransformer.toUserAttribute(attribute, userId));
    }

    @Override
    public void removeAttribute(String realm, String userName, AttributeDto attribute)
    {
        String userId = getUserId(userName, realmService.getRealmId(realm));
        userAttributeRepository.delete(userTransformer.toUserAttribute(attribute, userId));
    }

    @Override
    public void updateStatus(String realm, String userName, Status current, Status updateTo)
    {
        String realmId = realmService.getRealmId(realm);
        UserDetails user = userRepository.findByNameAndRealmIdAndStatus(userName, realmId, current);
        user.setStatus(updateTo);
        userRepository.save(user);
    }
}
