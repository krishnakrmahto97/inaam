package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Client
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @Basic
    @Column(name = "realm_id")
    private UUID realmId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "secret")
    private String secret;
}