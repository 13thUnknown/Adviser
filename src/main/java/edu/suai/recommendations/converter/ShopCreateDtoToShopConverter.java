package edu.suai.recommendations.converter;

import edu.suai.recommendations.dto.create.ShopCreateDto;
import edu.suai.recommendations.model.Shop;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShopCreateDtoToShopConverter extends AbstractConverter<ShopCreateDto, Shop> {

    @Override
    protected Shop convert(ShopCreateDto shopCreateDto) {
        if (shopCreateDto == null){
            return null;
        }
        return Shop.builder()
                .title(shopCreateDto.getTitle())
                .url(shopCreateDto.getUrl())
                .build();
    }
}
