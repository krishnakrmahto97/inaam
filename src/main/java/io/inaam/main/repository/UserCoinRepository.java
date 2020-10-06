package io.inaam.main.repository;

import io.inaam.main.entity.UserCoin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCoinRepository extends JpaRepository<UserCoin, String>
{
}
