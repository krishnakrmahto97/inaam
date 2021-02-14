package io.inaam.main.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Entity
@Table(name = "user_coin")
@IdClass(UserCoinPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCoin
{
    public UserCoin(String userId, String coinId)
    {
        this.userId = userId;
        this.coinId = coinId;
        this.balance = BigInteger.ZERO;
    }

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Id
    @Column(name = "coin_id", nullable = false)
    private String coinId;

    @Basic
    @Column(name = "balance")
    private BigInteger balance;

    @Embedded
    @Builder.Default
    private TimeAttribute timeAttribute = new TimeAttribute();
}
