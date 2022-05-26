package ru.tfs.spring.web.controller.handler;

import eu.tfs.spring.web.person.client.exception.PersonClientFallbackException;
import eu.tfs.spring.web.person.client.exception.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tfs.spring.web.medical.client.exception.MedicalClientFallbackException;
import ru.tfs.spring.web.medical.client.exception.PersonNotExistException;
import ru.tfs.spring.web.qr.client.exception.PassportNotExistException;
import ru.tfs.spring.web.qr.client.exception.QrServiceFallbackException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({PersonNotFoundException.class, PersonNotExistException.class, PassportNotExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String personNotFoundException() {
        return "Person not found";
    }

    @ExceptionHandler(PersonClientFallbackException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String clientPersonFallbackExceptionHandler(PersonClientFallbackException ex) {
        return "Server service-person unavailable. Try later";
    }

    @ExceptionHandler(MedicalClientFallbackException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String clientMedicalFallbackExceptionHandler(MedicalClientFallbackException ex) {
        return "Server service-medical unavailable. Try later";
    }

    @ExceptionHandler(QrServiceFallbackException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String clientQrFallbackExceptionHandler(QrServiceFallbackException ex) {
        return "Server service-qr unavailable. Try later";
    }
}
