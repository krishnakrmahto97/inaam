package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_details")
public class UserDetails
{
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Basic
    @Column(name = "realm_id", nullable = true)
    private UUID realmId;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private List<UserGroup> userGroupsById;

    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private List<UserAttribute> userAttributesById;

    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Collection<UserCoin> userCoinsById_0;

}
