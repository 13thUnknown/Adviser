package edu.suai.recommendations.dto.create;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OptionCreateDto {
    private long productId;

    private String criteriaTitle;

    private String value;
}
