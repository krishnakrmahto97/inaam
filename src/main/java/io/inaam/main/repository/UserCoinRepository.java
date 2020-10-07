package io.inaam.main.repository;

import io.inaam.main.entity.UserCoin;
import io.inaam.main.entity.UserCoinPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCoinRepository extends JpaRepository<UserCoin, UserCoinPK>
{
    List<UserCoin> findAllByUserId (String userId);
}
