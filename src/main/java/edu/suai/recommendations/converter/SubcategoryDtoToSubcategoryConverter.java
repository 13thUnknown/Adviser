package edu.suai.recommendations.converter;

import edu.suai.recommendations.dto.all.SubcategoryDto;
import edu.suai.recommendations.model.Subcategory;
import edu.suai.recommendations.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubcategoryDtoToSubcategoryConverter extends AbstractConverter<SubcategoryDto, Subcategory> {

    private final CategoryService categoryService;

    @Override
    protected Subcategory convert(SubcategoryDto subcategoryDto) {
        if (subcategoryDto == null){
            return null;
        }
        return Subcategory.builder()
                .title(subcategoryDto.getTitle())
                .description(subcategoryDto.getDescription())
                .category(categoryService.getCategoryByTitle(subcategoryDto.getCategory()))
                .build();
    }
}
