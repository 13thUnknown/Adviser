package edu.suai.recommendations.converter;

import edu.suai.recommendations.dto.create.OptionCreateDto;
import edu.suai.recommendations.model.Option;
import edu.suai.recommendations.model.Product;
import edu.suai.recommendations.service.CriteriaService;
import edu.suai.recommendations.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OptionCreateDtoToOptionConverter extends AbstractConverter<OptionCreateDto, Option> {

    private final CriteriaService criteriaService;
    private final ProductService productService;


    @Override
    protected Option convert(OptionCreateDto optionCreateDto) {
        if (optionCreateDto == null) {
            return null;
        }

        Product product = productService.getProductById(optionCreateDto.getProductId());
        return Option.builder()
                .criteria(criteriaService.getCriteriaByTitle(optionCreateDto.getCriteriaTitle()))
                .value(optionCreateDto.getValue())
                .products(Collections.singleton(product))
                .build();
    }
}
