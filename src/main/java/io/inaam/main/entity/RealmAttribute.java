package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "realm_attribute")
@IdClass(RealmAttributePK.class)
public class RealmAttribute
{
    @Id
    @Column(name = "realm_id", nullable = false)
    private String realmId;

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "value")
    private String value;
}
