package io.inaam.main.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "group_details")
public class GroupDetails
{
    @Id
    @Column(name = "id", nullable = false)
        @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")

    private String id;

    @Basic
    @Column(name = "realm_id")
    private String realmId;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

//    @OneToMany(mappedBy = "groupDetailsByGroupId")
//    private Collection<UserGroup> userGroupsById;

}
