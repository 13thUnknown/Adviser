package edu.suai.recommendations.model;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Set;

import static edu.suai.recommendations.common.Constants.CITEXT;

@Entity
@Table(name = "options")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Option extends BaseEntity {

    @NotNull
    private String value;

    @ManyToMany(mappedBy = "options", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> products;

    @ManyToMany(mappedBy = "options", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name = "criteria_id", foreignKey = @ForeignKey(name = "fk_options_criteria_id"))
    private Criteria criteria;
}
