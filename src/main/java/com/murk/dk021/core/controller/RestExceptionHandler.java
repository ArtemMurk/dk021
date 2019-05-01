package com.murk.dk021.core.controller;

import com.murk.dk021.core.exception.NotValidCodeException;
import com.murk.dk021.core.to.ExceptionTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(value= { NotValidCodeException.class})
    protected ResponseEntity<ExceptionTO> handleConflict(RuntimeException ex, WebRequest request)
    {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new ExceptionTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
