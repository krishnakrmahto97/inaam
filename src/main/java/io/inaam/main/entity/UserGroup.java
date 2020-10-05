package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_group")
@IdClass(UserGroupPK.class)
public class UserGroup
{
    @Id
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Id
    @Column(name = "group_id", nullable = false)
    private UUID groupId;
}
