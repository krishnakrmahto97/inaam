package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class RealmAttributePK implements Serializable
{
    @Column(name = "realm_id", nullable = false)
    @Id
    private String realmId;

    @Column(name = "name", nullable = false)
    @Id
    private String name;

}
