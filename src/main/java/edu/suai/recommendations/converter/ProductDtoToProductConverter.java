package edu.suai.recommendations.converter;

import edu.suai.recommendations.dto.all.ProductDto;
import edu.suai.recommendations.model.Product;
import edu.suai.recommendations.service.CategoryService;
import edu.suai.recommendations.service.ShopService;
import edu.suai.recommendations.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.Money;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class ProductDtoToProductConverter extends AbstractConverter<ProductDto, Product> {

    private final CategoryService categoryService;
    private final SubcategoryService subcategoryService;
    private final ShopService shopService;

    @Override
    protected Product convert(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        BigDecimal price = new BigDecimal("24.455");
        BigDecimal displayVal = price.setScale(2, RoundingMode.HALF_EVEN);
        Product product = Product.builder()
                .category(categoryService.getCategoryByTitle(productDto.getCategory()))
                .description(productDto.getDescription())
                .shop(shopService.getShopByTitle(productDto.getShop()))
                .subcategory(subcategoryService.getSubcategoryByTitle(productDto.getSubcategory()))
                .title(productDto.getTitle())
                .url(productDto.getUrl())
                .price(displayVal)
                .build();
        return product;
    }
}
