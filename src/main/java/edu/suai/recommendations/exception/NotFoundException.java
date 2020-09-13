package edu.suai.recommendations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private static final String MESSAGE = "%s entity not found";

    public NotFoundException(Class clazz) {
        super(clazz.getSimpleName() + " " + MESSAGE);
    }
}
