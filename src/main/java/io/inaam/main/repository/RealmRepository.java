package io.inaam.main.repository;

import io.inaam.main.entity.Realm;
import io.inaam.main.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealmRepository extends JpaRepository<Realm, String>
{
    Realm findByNameAndStatus(String name, Status status);

    default Realm findByName(String name)
    {
        return findByNameAndStatus(name, Status.Active);
    }

    List<Realm> findAllByStatus(Status status);

    default List<Realm> findAllActive()
    {
        return findAllByStatus(Status.Active);
    }
}
