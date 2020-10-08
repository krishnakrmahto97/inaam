package io.inaam.main.service;

import io.inaam.main.dto.AttributeDto;
import io.inaam.main.dto.RealmDto;
import io.inaam.main.entity.Realm;
import io.inaam.main.entity.Status;
import io.inaam.main.exception.RealmException;
import io.inaam.main.repository.RealmAttributeRepository;
import io.inaam.main.repository.RealmRepository;
import io.inaam.main.transformer.RealmTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RealmServiceImpl implements RealmService
{
    private final RealmRepository realmRepository;
    private final RealmAttributeRepository realmAttributeRepository;
    private final RealmTransformer realmTransformer;

    @Override
    public String getRealmId(String name)
    {
        return getRealmEntity(name).getId();
    }

    public RealmDto getRealm(String name)
    {
        Realm realmEntity = getRealmEntity(name);
        return realmTransformer.toRealmDto(realmEntity);
    }

    private Realm getRealmEntity(String name)
    {
        return Optional.ofNullable(realmRepository.findByName(name))
                       .orElseThrow(() -> new RealmException(RealmException.NO_REALM_FOUND));
    }

    @Override
    public void createRealm(RealmDto realmDto)
    {
        Realm realm = realmTransformer.toRealm(realmDto);
        String realmId = realmRepository.save(realm).getId();

        Optional.ofNullable(realmDto.getAttributes())
                .ifPresent(attributes -> attributes.stream()
                                                   .map(attribute -> realmTransformer.toRealmAttribute(attribute, realmId))
                                                   .forEach(realmAttributeRepository::save));
    }

    @Override
    public List<RealmDto> listRealm()
    {
        return realmTransformer.toRealmDtoList(realmRepository.findAllActive());
    }

    @Override
    public void updateStatus(String realmName, Status currentStatus, Status updatedStatus)
    {
        Realm realmEntity = realmRepository.findByNameAndStatus(realmName, currentStatus);
        realmEntity.setStatus(updatedStatus);
        realmRepository.save(realmEntity);
    }

    @Override
    public void addAttribute(String realm, AttributeDto attribute)
    {
        String realmId = getRealmId(realm);
        realmAttributeRepository.save(realmTransformer.toRealmAttribute(attribute, realmId));
    }

    @Override
    public void removeAttribute(String realm, AttributeDto attribute)
    {
        String realmId = getRealmId(realm);
        realmAttributeRepository.save(realmTransformer.toRealmAttribute(attribute, realmId));
    }
}
