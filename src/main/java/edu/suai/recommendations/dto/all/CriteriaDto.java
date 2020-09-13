package edu.suai.recommendations.dto.all;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CriteriaDto {
    private String title;

    private boolean numeric;
}
