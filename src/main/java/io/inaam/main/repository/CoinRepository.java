package io.inaam.main.repository;

import io.inaam.main.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoinRepository extends JpaRepository<Coin, String>
{
    List<Coin> findAllByRealmId (String realmId);
    Optional<Coin> findByRealmIdAndName(String realmId, String name);
}
