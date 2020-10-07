package io.inaam.main.repository;

import io.inaam.main.entity.CoinTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoinTransactionRepository extends JpaRepository<CoinTransaction, String>
{
    List<CoinTransaction> findAllByRealmIdAndUserId(String realmId, String userId);
}
