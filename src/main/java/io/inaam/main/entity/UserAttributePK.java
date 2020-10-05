package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Data
public class UserAttributePK implements Serializable
{
    @Column(name = "user_id", nullable = false)
    @Id
    private UUID userId;

    @Column(name = "name", nullable = false)
    @Id
    private UUID name;
}
