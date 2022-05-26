package eu.tfs.spring.web.model.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PathErrorMessage {
    MISSING_PARAMETER("Missing parameter with name '%s'"),
    WRONG_TYPE_ARGUMENT("Parameter with name '%s' has wrong type");

    private final String message;
}
