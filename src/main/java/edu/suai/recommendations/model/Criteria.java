package edu.suai.recommendations.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.Set;

import static edu.suai.recommendations.common.Constants.CITEXT;

@Entity
@Table(name = "criteria")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Criteria extends BaseEntity {
    @NotNull
    @Column(unique = true, columnDefinition = CITEXT)
    private String title;

    @NotNull
    @Column(name = "is_numeric")
    boolean isNumeric;

    @OneToMany(mappedBy="criteria")
    private Set<Option> options;

}
