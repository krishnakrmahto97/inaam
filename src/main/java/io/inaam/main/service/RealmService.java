package io.inaam.main.service;

import io.inaam.main.dto.RealmDto;
import io.inaam.main.entity.Realm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealmService
{
    String getRealmId(String name);

    Realm getRealm(String name);

    void createRealm(RealmDto realm);

    List<RealmDto> listRealm();
}
