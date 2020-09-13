package edu.suai.recommendations.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Set;

import static edu.suai.recommendations.common.Constants.CITEXT;

@Entity
@Table(name = "products")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product extends BaseEntity {
    @NotNull
    @Column(columnDefinition = CITEXT)
    private String title;

    @NotNull
    @Column(name="price",columnDefinition="money")
    private BigDecimal price;

    private String description;

    @NotNull
    private String url;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> users;

    @ManyToMany(mappedBy = "recommendations", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> recommended;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_products_category_id"))
    private Category category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "subcategory_id", foreignKey = @ForeignKey(name = "fk_products_subcategory_id"))
    private Subcategory subcategory;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "shop_id", foreignKey = @ForeignKey(name = "fk_products_shop_id"))
    private Shop shop;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "products_options",
            joinColumns = @JoinColumn(name = "product_id",
                    foreignKey = @ForeignKey(name = "fk_products_options_product_id")),
            inverseJoinColumns = @JoinColumn(name = "option_id",
                    foreignKey = @ForeignKey(name = "fk_products_options_option_id")))
    private Set<Option> options;
}
