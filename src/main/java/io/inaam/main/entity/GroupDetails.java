package io.inaam.main.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
