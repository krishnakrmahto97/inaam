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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RealmServiceImpl implements RealmService
{
    private final RealmRepository realmRepository;
    private final RealmAttributeRepository realmAttributeRepository;
    private final RealmTransformer realmTransformer;

    @Override
    public Realm getRealm(String name)
    {
        return realmRepository.findByName(name);
    }

    @Override
    public RealmDto createRealm(RealmDto realmDto)
    {
        Realm realm = realmTransformer.toRealm(realmDto);
        String realmId = realmRepository.save(realm).getId();
        Optional.ofNullable(realmDto.getAttributes())
                .ifPresent(attributes -> attributes.stream()
                                                   .map(attribute -> realmTransformer.toRealmAttribute(attribute, realmId))
                                                   .forEach(realmAttributeRepository::save));

        return realmTransformer.toRealmDto(realmRepository.findById(realmId).get());
    }

    @Override
    public List<RealmDto> listRealm()
    {
        return realmRepository.findAll()
                              .stream()
                              .map(realmTransformer::toRealmDto)
                              .collect(Collectors.toList());
    }

}
