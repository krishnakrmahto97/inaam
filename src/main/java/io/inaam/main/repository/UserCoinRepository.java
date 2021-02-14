package io.inaam.main.repository;

import io.inaam.main.entity.UserCoin;
import io.inaam.main.entity.UserCoinPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCoinRepository extends JpaRepository<UserCoin, UserCoinPK>
{
    List<UserCoin> findAllByUserId (String userId);

    @Query("select uc from UserCoin uc where uc.userId = :userId and uc.balance <> 0 order by uc.creationTime desc")
    List<UserCoin> findAllByUserIdAAndBalanceNotZeroOrderByCreationTime(String userId);
}
