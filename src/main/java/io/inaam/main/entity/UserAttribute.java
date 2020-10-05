package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_attribute")
@IdClass(UserAttributePK.class)
public class UserAttribute
{
    @Id
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Id
    @Column(name = "name", nullable = false)
    private UUID name;

    @Basic
    @Column(name = "value")
    private String value;
}
