package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Realm
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "currency_symbol")
    private String currencySymbol;

    @OneToMany
    @JoinColumn(name = "id",referencedColumnName = "realm_id")
    List<RealmAttribute> realmAttributes;
}
