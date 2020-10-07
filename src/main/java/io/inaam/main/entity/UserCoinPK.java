package io.inaam.main.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCoinPK implements Serializable
{
    @Column(name = "user_id", nullable = false)
    @Id
    private String userId;

    @Column(name = "coin_id", nullable = false)
    @Id
    private String coinId;

}
