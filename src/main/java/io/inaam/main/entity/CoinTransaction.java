package io.inaam.main.entity;

import io.inaam.main.util.CoinTransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "coin_transaction")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoinTransaction
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Basic
    @Column(name = "realm_id")
    private String realmId;

    @Basic
    @Column(name = "user_id")
    private String userId;

    @Basic
    @Column(name = "coin_id")
    private String coinId;

    @Basic
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CoinTransactionType type;

    @Basic
    @Column(name = "coins_transacted")
    private Integer coinsTransacted;
}
