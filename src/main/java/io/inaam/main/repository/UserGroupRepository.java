package io.inaam.main.repository;

import io.inaam.main.entity.UserGroup;
import io.inaam.main.entity.UserGroupPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, UserGroupPK>
{
    List<UserGroup> findAllByGroupId(String groupId);
}
