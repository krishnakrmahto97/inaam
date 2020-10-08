package io.inaam.main.repository;

import io.inaam.main.entity.Status;
import io.inaam.main.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, String>
{
    List<UserDetails> findByRealmIdAndStatus(String realmId, Status status);

    default List<UserDetails> findByRealmId(String realmId)
    {
        return findByRealmIdAndStatus(realmId, Status.Active);
    }

    UserDetails findByNameAndRealmIdAndStatus(String name, String realmId, Status status);

    default UserDetails findByNameAndRealmId(String name, String realmId)
    {
        return findByNameAndRealmIdAndStatus(name, realmId, Status.Active);
    }

    List<UserDetails> findAllByIdInAndStatus(List<String> userIds,Status status);

    default List<UserDetails> findAllByIdIn(List<String> userIds)
    {
        return findAllByIdInAndStatus(userIds,Status.Active);
    }
}
