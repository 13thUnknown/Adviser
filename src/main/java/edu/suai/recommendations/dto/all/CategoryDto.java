package edu.suai.recommendations.dto.all;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
    private String title;

    private String description;
}
