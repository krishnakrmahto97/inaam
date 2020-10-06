package io.inaam.main.repository;

import io.inaam.main.entity.CoinTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinTransactionRepository extends JpaRepository<CoinTransaction, String>
{
}
