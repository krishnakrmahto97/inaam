package io.inaam.main.repository;

import io.inaam.main.entity.UserAttribute;
import io.inaam.main.entity.UserAttributePK;
import org.springframework.data.repository.CrudRepository;

public interface UserAttributeRepository extends CrudRepository<UserAttribute, UserAttributePK>
{
}
