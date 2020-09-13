package edu.suai.recommendations.dto.update;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopUpdateDto {
    private String title;

    private String url;
}
