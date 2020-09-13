package edu.suai.recommendations.model;

import edu.suai.recommendations.exception.OptimisticLockingException;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static edu.suai.recommendations.common.Constants.CITEXT;

@Entity
@Table(name = "users")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User implements Serializable, UserDetails {

    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,}$")
    private String login;

    @NotNull
    @Email
    @Column(unique = true, columnDefinition = CITEXT)
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",
                    foreignKey = @ForeignKey(name = "fk_users_roles_user_id")),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    foreignKey = @ForeignKey(name = "fk_users_roles_role_id")))
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_products",
            joinColumns = @JoinColumn(name = "user_id",
                    foreignKey = @ForeignKey(name = "fk_user_product_user_id")),
            inverseJoinColumns = @JoinColumn(name = "product_id",
                    foreignKey = @ForeignKey(name = "fk_user_product_product_id")))
    private Set<Product> products;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "recommendations",
            joinColumns = @JoinColumn(name = "user_id",
                    foreignKey = @ForeignKey(name = "fk_recommendations_user_id")),
            inverseJoinColumns = @JoinColumn(name = "product_id",
                    foreignKey = @ForeignKey(name = "fk_recommendations_product_id")))
    private Set<Product> recommendations;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_options",
            joinColumns = @JoinColumn(name = "user_id",
                    foreignKey = @ForeignKey(name = "fk_users_options_user_id")),
            inverseJoinColumns = @JoinColumn(name = "option_id",
                    foreignKey = @ForeignKey(name = "fk_users_options_option_id")))
    private Set<Option> options;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>(roles);
        return authorities;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
}
