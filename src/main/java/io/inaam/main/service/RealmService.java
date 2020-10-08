package io.inaam.main.service;

import io.inaam.main.dto.AttributeDto;
import io.inaam.main.dto.RealmDto;
import io.inaam.main.entity.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealmService
{
    String getRealmId(String name);

    RealmDto getRealm(String name);

    void createRealm(RealmDto realm);

    List<RealmDto> listRealm();

    void updateStatus(String realmName, Status currentStatus, Status updatedStatus);

    void addAttribute(String realm, AttributeDto attribute);

    void removeAttribute(String realm, AttributeDto attribute);
}
