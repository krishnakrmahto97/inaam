package io.inaam.main.repository;

import io.inaam.main.entity.GroupDetails;
import io.inaam.main.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupDetails, String>
{
    List<GroupDetails> findByRealmIdAndStatus(String realmId, Status status);

    default List<GroupDetails> findByRealmId(String realmId)
    {
        return findByRealmIdAndStatus(realmId, Status.Active);
    }

    GroupDetails findByNameAndRealmIdAndStatus(String name, String realmId, Status status);

    default GroupDetails findByNameAndRealmId(String name, String realmId)
    {
        return findByNameAndRealmIdAndStatus(name, realmId, Status.Active);
    }
}
