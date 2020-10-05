package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
public class UserGroupPK implements Serializable
{
    @Column(name = "user_id", nullable = false)
    @Id
    private UUID userId;

    @Column(name = "group_id", nullable = false)
    @Id
    private UUID groupId;

}
