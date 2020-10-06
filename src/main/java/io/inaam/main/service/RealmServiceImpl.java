package io.inaam.main.service;

import io.inaam.main.repository.RealmRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RealmServiceImpl implements RealmService {

    private final RealmRepository realmRepository;

    @Override
    public String getRealmId(String realmName)
    {
        return realmRepository.findRealmByName(realmName).getId();
    }
}