package io.inaam.main.repository;

import io.inaam.main.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, String>
{
    List<UserDetails> findByRealmId(String realmId);

    UserDetails findByNameAndRealmId(String name, String realmId);

    List<UserDetails> findAllByIdIn(List<String> userIds);
}