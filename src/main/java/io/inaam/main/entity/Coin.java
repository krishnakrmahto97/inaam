package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@IdClass(CoinPK.class)
public class Coin
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @Id
    @Column(name = "realm_id", nullable = false)
    private UUID realmId;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "conversion_rate", precision = 2)
    private BigDecimal conversionRate;
}
