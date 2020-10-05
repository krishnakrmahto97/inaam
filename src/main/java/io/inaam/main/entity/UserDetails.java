package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<UserGroup> groups;

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<UserAttribute> attributes;

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<UserCoin> coins;

}
