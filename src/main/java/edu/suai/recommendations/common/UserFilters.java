package edu.suai.recommendations.common;

import io.swagger.annotations.ApiParam;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFilters {
    @ApiParam(value = "Key for searching users by login or email"
            , example = "user@gmail.com")
    private String search = Constants.DEFAULT_SEARCH_VALUE;

    @ApiParam(value = "Date of creation for users that were created before")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime creationDateBefore;

    @ApiParam(value = "Date of creation for users that were created after")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime creationDateAfter;

    @ApiParam(value = "Date of update for users that were modified before")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime updateDateBefore;

    @ApiParam(value = "Date of update for users that were modified after")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime updateDateAfter;
}
