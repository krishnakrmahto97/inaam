package io.inaam.main.repository;

import io.inaam.main.entity.Realm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealmRepository extends JpaRepository<Realm, String>
{
    Realm findByName(String name);
}
