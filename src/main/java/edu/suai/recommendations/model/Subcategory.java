package edu.suai.recommendations.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Set;

import static edu.suai.recommendations.common.Constants.CITEXT;

@Entity
@Table(name = "subcategories")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Subcategory extends BaseEntity {
    @NotNull
    @Column(unique = true, columnDefinition = CITEXT)
    private String title;

    private String description;

    @OneToMany(mappedBy="subcategory")
    private Set<Product> products;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_subcategory_category_id"))
    private Category category;

}
