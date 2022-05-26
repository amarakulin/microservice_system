package ru.tfs.spring.web.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tfs.spring.web.qr.client.exception.PassportNotExistException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(PassportNotExistException.class)
    public ResponseEntity<String> handlePassportNotExistException(PassportNotExistException ex) {
        return new ResponseEntity<>("Passport with number '%s' not exist".formatted(ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
