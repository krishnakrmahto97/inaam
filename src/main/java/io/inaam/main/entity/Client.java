package io.inaam.main.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
public class Client
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
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "secret")
    private String secret;
}