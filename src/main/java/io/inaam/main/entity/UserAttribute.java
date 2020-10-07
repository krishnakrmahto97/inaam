package io.inaam.main.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
