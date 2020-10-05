package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_details")
public class UserDetails
{
    @Id
    @Column(name = "id", nullable = false)

    private String id;

    @Basic
    @Column(name = "realm_id", nullable = true)
    private String realmId;

    @Basic
    @Column(name = "name")
    private String name;

//    @OneToMany
//    @JoinColumn(name = "id", referencedColumnName = "user_id", insertable = false, updatable = false)
//    private List<UserGroup> userGroupsById;
//
//    @OneToMany
//    @JoinColumn(name = "id", referencedColumnName = "user_id", insertable = false, updatable = false)
//    private List<UserAttribute> userAttributesById;
//
//    @OneToMany
//    @JoinColumn(name = "id", referencedColumnName = "user_id", insertable = false, updatable = false)
//    private Collection<UserCoin> userCoinsById_0;

}
