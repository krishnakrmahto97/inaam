package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Data
public class CoinPK implements Serializable
{
    @Column(name = "id", nullable = false)
    @Id
    private UUID id;

    @Column(name = "realm_id", nullable = false)
    @Id
    private UUID realmId;

}
