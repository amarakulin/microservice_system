package eu.tfs.spring.web.person.client.exception;

public class WrongValueParameterException extends Exception {
    public WrongValueParameterException(String message) {
        super(message);
    }
}
