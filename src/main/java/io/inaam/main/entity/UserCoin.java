package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_coin")
@IdClass(UserCoinPK.class)
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
}
