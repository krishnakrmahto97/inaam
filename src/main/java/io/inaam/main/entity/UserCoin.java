package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_coin")
@IdClass(UserCoinPK.class)
public class UserCoin
{
    @Id
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Id
    @Column(name = "coin_id", nullable = false)
    private UUID coinId;

    @OneToOne
    @JoinColumn(name = "coin_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Coin coin;

    @Basic
    @Column(name = "balance")
    private BigInteger balance;
}
