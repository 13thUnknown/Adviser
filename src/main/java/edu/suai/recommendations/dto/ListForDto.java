package edu.suai.recommendations.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ListForDto<T> {

    private long count;

    private List<T> results;
}