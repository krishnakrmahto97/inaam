package io.inaam.main.repository;

import io.inaam.main.entity.RealmAttribute;
import io.inaam.main.entity.RealmAttributePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealmAttributeRepository extends JpaRepository<RealmAttribute, RealmAttributePK>
{
}
