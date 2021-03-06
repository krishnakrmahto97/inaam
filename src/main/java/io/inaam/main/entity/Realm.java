package io.inaam.main.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
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

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.Active;

    @OneToMany
    @JoinColumn(name = "realm_id", referencedColumnName = "id", updatable = false, insertable = false)
    @Builder.Default
    List<RealmAttribute> attributes = new ArrayList<>();

}
