package io.inaam.main.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_coin")
@IdClass(UserCoinPK.class)
@AllArgsConstructor
@NoArgsConstructor
public class UserCoin
{
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Id
    @Column(name = "coin_id", nullable = false)
    private String coinId;

    @Basic
    @Column(name = "balance")
    private int balance;

    @Embedded
    @Builder.Default
    private TimeAttribute timeAttribute = new TimeAttribute();
}
