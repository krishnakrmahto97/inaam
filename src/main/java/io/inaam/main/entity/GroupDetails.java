package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "group_details")
public class GroupDetails
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @Basic
    @Column(name = "realm_id")
    private UUID realmId;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "groupDetailsByGroupId")
    private Collection<UserGroup> userGroupsById;

}
