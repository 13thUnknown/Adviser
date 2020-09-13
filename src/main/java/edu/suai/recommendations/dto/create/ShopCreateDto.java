package edu.suai.recommendations.dto.create;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopCreateDto {
    private String title;

    private String url;
}
