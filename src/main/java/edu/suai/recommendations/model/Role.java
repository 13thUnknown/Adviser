package edu.suai.recommendations.model;

import edu.suai.recommendations.exception.OptimisticLockingException;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

import static edu.suai.recommendations.common.Constants.CITEXT;

@Entity
@Table(name = "roles")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Role implements Serializable, GrantedAuthority {
    @NotNull
    @Column(unique = true, columnDefinition = CITEXT)
    private String title;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> users;

    @Override
    public String getAuthority() {
        return getTitle();
    }

    public String getTitle(){
        return title;
    }

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

    @Override
    public String toString(){
        return getTitle();
    }
}
