package io.inaam.main.repository;

import io.inaam.main.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoinRepository extends JpaRepository<Coin, String>
{
    List<Coin> findAllByRealmId (String realmId);
    Coin findByRealmIdAndName (String realmId, String name);
}
