package io.inaam.main.repository;

import io.inaam.main.entity.Realm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealmRepository extends JpaRepository<Realm, String>
{
    Realm findRealmByName(String name);
}
