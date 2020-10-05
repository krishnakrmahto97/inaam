package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "realm_attribute")
@IdClass(RealmAttributePK.class)
public class RealmAttribute
{
    @Id
    @Column(name = "realm_id", nullable = false)
    @GeneratedValue
    private UUID realmId;

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "value")
    private String value;
}
