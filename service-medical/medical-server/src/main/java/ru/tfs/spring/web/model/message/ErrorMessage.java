package ru.tfs.spring.web.model.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    PERSON_NOT_FOUND("Person not found"),
    SERVER_ERROR("There is error in server side. Try later or report the incident to our support team"),
    NOT_FOUND_VACCINATION_POINT("VaccinationPoint with id: '%s' wasn't found"),
    NOT_FOUND_VACCINE("Vaccine with id: '%s' wasn't found"),
    NOT_FOUND_ADDRESS("Address with id: '%s' wasn't found"),
    SEND_QR_INFO_FAILURE("Failed to send personName '%s' on date '%s' to create QR");

    private final String message;
}
