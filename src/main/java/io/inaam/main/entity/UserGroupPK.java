package io.inaam.main.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupPK implements Serializable
{
    @Column(name = "user_id", nullable = false)
    @Id
    private String userId;

    @Column(name = "group_id", nullable = false)
    @Id
    private String groupId;

}
