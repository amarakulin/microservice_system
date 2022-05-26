package eu.tfs.spring.web.model.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ValueErrorMessage {
    CAN_NOT_BE_NEGATIVE("Value can't not be negative"),
    CAN_NOT_BE_NEGATIVE_OR_ZERO("Value can't not be negative or zero");

    private final String message;
}
