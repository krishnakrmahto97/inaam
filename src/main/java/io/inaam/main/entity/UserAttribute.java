package io.inaam.main.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_attribute")
@IdClass(UserAttributePK.class)
public class UserAttribute
{
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "value")
    private String value;
}
