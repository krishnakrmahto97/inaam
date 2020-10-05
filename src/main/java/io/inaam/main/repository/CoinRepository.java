package io.inaam.main.repository;

import io.inaam.main.entity.Coin;
import io.inaam.main.entity.CoinPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoinRepository extends JpaRepository<Coin, CoinPK>
{
    List<Coin> findAllByRealmId (String realmId);
}