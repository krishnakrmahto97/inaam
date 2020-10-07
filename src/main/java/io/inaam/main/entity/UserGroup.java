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
@Table(name = "user_group")
@IdClass(UserGroupPK.class)
public class UserGroup
{
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Id
    @Column(name = "group_id", nullable = false)
    private String groupId;

    @OneToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", insertable = false, updatable = false)
    private GroupDetails groupDetails;
}
