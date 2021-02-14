package io.inaam.main.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

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
    @Column(name = "balance", nullable = false)
    private BigInteger balance;

    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @UpdateTimestamp
    @Column(name = "last_modified_time")
    private LocalDateTime lastModifiedTime;
}
