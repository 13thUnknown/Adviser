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
@Table(name = "shops")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Shop extends BaseEntity {
    @NotNull
    @Column(unique = true, columnDefinition = CITEXT)
    private String title;

    @NotNull
    private String url;

    @OneToMany(mappedBy="shop")
    private Set<Product> products;
}
