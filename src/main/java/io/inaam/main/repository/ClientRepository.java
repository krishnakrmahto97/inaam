package io.inaam.main.repository;

import io.inaam.main.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    List<Client> findByRealmIdEquals(String realmId);

    List<Client> findByNameEqualsAndRealmIdEquals(String name, String realmid);

}