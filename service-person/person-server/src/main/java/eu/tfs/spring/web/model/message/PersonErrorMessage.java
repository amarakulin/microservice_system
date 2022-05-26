package eu.tfs.spring.web.model.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PersonErrorMessage {
    PERSON_NOT_FOUND("Person not found"),
    PERSON_EXIST("Person already exist");

    private final String message;
}
