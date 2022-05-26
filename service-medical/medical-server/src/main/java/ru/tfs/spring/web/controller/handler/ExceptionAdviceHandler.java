package ru.tfs.spring.web.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.tfs.spring.web.medical.client.exception.InitializationDBException;
import ru.tfs.spring.web.medical.client.exception.PersonClientFallbackException;
import ru.tfs.spring.web.medical.client.exception.PersonNotExistException;
import ru.tfs.spring.web.model.message.ErrorMessage;

@Slf4j
@ControllerAdvice
public class ExceptionAdviceHandler {

    @ExceptionHandler(PersonClientFallbackException.class)
    public ResponseEntity<String> handlePersonClientException(PersonClientFallbackException ex) {
        return new ResponseEntity<>(ErrorMessage.SERVER_ERROR.getMessage(),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(InitializationDBException.class)
    public ResponseEntity<String> handlePersonClientException(InitializationDBException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ErrorMessage.SERVER_ERROR.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PersonNotExistException.class)
    public ResponseEntity<String> handlePersonNotExistException(PersonNotExistException ex) {
        return new ResponseEntity<>(ErrorMessage.PERSON_NOT_FOUND.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
}
