package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_group")
@IdClass(UserGroupPK.class)
public class UserGroup
{
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Id
    @Column(name = "group_id", nullable = false)
    private String groupId;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "group_id", insertable = false, updatable = false)
    private GroupDetails groupDetails;
}
