package eu.tfs.spring.web.controller.handler;

import eu.tfs.spring.web.person.client.exception.PersonExistException;
import eu.tfs.spring.web.person.client.exception.PersonNotFoundException;
import eu.tfs.spring.web.person.client.exception.RegionNotExistException;
import eu.tfs.spring.web.person.client.exception.WrongValueParameterException;
import eu.tfs.spring.web.model.message.AddressErrorMessage;
import eu.tfs.spring.web.model.message.PathErrorMessage;
import eu.tfs.spring.web.model.message.PersonErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(PersonExistException.class)
    public ResponseEntity<String> handlePersonExistException(PersonExistException ex) {
        return new ResponseEntity<>(PersonErrorMessage.PERSON_EXIST.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegionNotExistException.class)
    public ResponseEntity<String> handleRegionNotExistException(RegionNotExistException ex) {
        return new ResponseEntity<>(AddressErrorMessage.REGION_NOT_EXIST.getMessage().formatted(ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<String> handlePersonNotFoundException(PersonNotFoundException ex) {
        return new ResponseEntity<>(PersonErrorMessage.PERSON_NOT_FOUND.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingRequestParameterException(MissingServletRequestParameterException ex) {
        return new ResponseEntity<>(
                String.format(PathErrorMessage.MISSING_PARAMETER.getMessage(), ex.getParameterName()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(
                String.format(PathErrorMessage.WRONG_TYPE_ARGUMENT.getMessage(), ex.getName()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongValueParameterException.class)
    public ResponseEntity<String> handleWrongValueParameterException(WrongValueParameterException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
       String errorMessages = ex.getAllErrors().stream()
               .map(ObjectError::getDefaultMessage)
               .collect(Collectors.joining("\n"));
       return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessages = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }
}
