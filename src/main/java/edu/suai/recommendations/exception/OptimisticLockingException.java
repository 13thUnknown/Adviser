package edu.suai.recommendations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class OptimisticLockingException extends RuntimeException {
    private static final String MESSAGE = "%s entity has been already changed. Current version is %s doesn't equal %s";

    public OptimisticLockingException(long version, long newVersion, Class clazz) {
        super(String.format(MESSAGE, clazz.getSimpleName(), version, newVersion));
    }
}