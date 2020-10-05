package io.inaam.main.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Realm
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "currency_symbol")
    private String currencySymbol;
//
//    @OneToMany
//    @JoinColumn(name = "id",referencedColumnName = "realm_id")
//    List<RealmAttribute> realmAttributes;
}
