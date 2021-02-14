package io.inaam.main.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Embeddable
@Getter
@Setter
public class TimeAttribute implements Serializable
{
    @Column(name = "creation_time")
    private Timestamp creationTime;

    @Column(name = "last_modified_time")
    private Timestamp lastModifiedTime;

    @PrePersist
    protected void onCreate()
    {
        creationTime = Timestamp.from(Instant.now());
        lastModifiedTime = Timestamp.from(Instant.now());
    }

    @PreUpdate
    protected void onUpdate()
    {
        lastModifiedTime = Timestamp.from(Instant.now());
    }
}
