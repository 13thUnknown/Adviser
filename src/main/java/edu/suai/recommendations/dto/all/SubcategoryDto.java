package edu.suai.recommendations.dto.all;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubcategoryDto {
    private String title;

    private String description;

    private String category;
}
