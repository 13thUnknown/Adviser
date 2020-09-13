package edu.suai.recommendations.converter;

import edu.suai.recommendations.dto.all.CriteriaDto;
import edu.suai.recommendations.model.Criteria;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CriteriaDtoToCriteriaConverter extends AbstractConverter<CriteriaDto, Criteria> {
    @Override
    protected Criteria convert(CriteriaDto criteriaDto) {
        if (criteriaDto == null){
            return null;
        }
        return Criteria.builder()
                .title(criteriaDto.getTitle())
                .isNumeric(criteriaDto.isNumeric())
                .build();
    }
}
