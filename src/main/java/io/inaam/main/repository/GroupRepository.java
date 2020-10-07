package io.inaam.main.repository;

import io.inaam.main.entity.GroupDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupDetails, String>
{
    List<GroupDetails> findByRealmId(String realmId);

    GroupDetails findByNameAndRealmId(String name, String realmId);
}
