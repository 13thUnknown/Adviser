package edu.suai.recommendations.dto.all;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private String title;

    private String description;

    private String url;

    private String category;

    private String subcategory;

    private String shop;
}
