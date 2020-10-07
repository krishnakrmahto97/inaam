package io.inaam.main.service;

import io.inaam.main.dto.RealmDto;
import io.inaam.main.entity.Realm;
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
        return getRealm(name).getId();
    }

    @Override
    public Realm getRealm(String name)
    {
        return realmRepository.findByName(name);
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
        List<Realm> realms = realmRepository.findAll();
        // TODO: [Optional]
        //  refactor method name toRealmDto to toRealmDtos or toRealmDtoList
        //  realmTransformer.toRealmDtoList(realmRepository.findAll())
        return realmTransformer.toRealmDto(realms);
    }

}
