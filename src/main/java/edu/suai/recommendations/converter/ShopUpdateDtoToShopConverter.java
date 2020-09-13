package edu.suai.recommendations.converter;

import edu.suai.recommendations.dto.update.ShopUpdateDto;
import edu.suai.recommendations.model.Shop;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShopUpdateDtoToShopConverter extends AbstractConverter<ShopUpdateDto, Shop> {

    @Override
    protected Shop convert(ShopUpdateDto shopUpdateDto) {
        if (shopUpdateDto == null){
            return null;
        }
        return Shop.builder()
                .title(shopUpdateDto.getTitle())
                .url(shopUpdateDto.getUrl())
                .build();
    }
}
