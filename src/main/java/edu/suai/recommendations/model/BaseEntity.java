package edu.suai.recommendations.model;

import edu.suai.recommendations.exception.OptimisticLockingException;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp DEFAULT current_timestamp", insertable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "timestamp DEFAULT current_timestamp")
    private ZonedDateTime updatedAt;

    @Column(columnDefinition = "bigint default 1")
    @Version
    @NotNull
    private long version = 1;

    public void throwExceptionIfModified(long version) {
        if (this.version != version) {
            throw new OptimisticLockingException(this.version, version, getClass());
        }
    }
}

