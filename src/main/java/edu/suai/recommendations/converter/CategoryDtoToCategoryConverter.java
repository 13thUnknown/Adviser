package edu.suai.recommendations.converter;

import edu.suai.recommendations.dto.all.CategoryDto;
import edu.suai.recommendations.model.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryDtoToCategoryConverter extends AbstractConverter<CategoryDto, Category> {

    @Override
    protected Category convert(CategoryDto categoryDto) {
        if (categoryDto == null){
            return null;
        }
        return Category.builder()
                .title(categoryDto.getTitle())
                .description(categoryDto.getDescription())
                .build();
    }
}
