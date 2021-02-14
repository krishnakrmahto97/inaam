package io.inaam.main.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
public class Coin
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "realm_id", nullable = false)
    private String realmId;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "monetary_value_per_coin", precision = 2)
    private BigDecimal monetaryValuePerCoin;

    @Basic
    @Column(name = "monetary_amount_to_earn_one_coin")
    private int monetaryAmountToEarnOneCoin;

    @Embedded
    @Builder.Default
    private TimeAttribute timeAttribute = new TimeAttribute();
}
