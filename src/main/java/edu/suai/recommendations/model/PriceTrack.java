package edu.suai.recommendations.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static edu.suai.recommendations.common.Constants.CITEXT;

@Entity
@Table(name = "price_track")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PriceTrack extends BaseEntity {
    @NotNull
    @Column(unique = true, columnDefinition = CITEXT)
    private Double price;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_price_track_product_id"))
    private Product product;
}
