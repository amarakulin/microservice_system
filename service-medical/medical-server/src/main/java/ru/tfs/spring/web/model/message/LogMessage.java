package ru.tfs.spring.web.model.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogMessage {
    FALLBACK_PERSON_CLIENT("Person client is fallback"),
    DID_NOT_SAVE_PERSON("Didn't save the peron with name '%s'"),
    SAVING_FILE("Saving file with filename: '%s'"),
    PARSING_FILE("Parsing file with filename: '%s'"),
    SAVED_FILE("File with filename: '%s' has been saved"),
    SAVING_PERSON("Saving person with name: '%s'"),
    SAVED_PERSON("Person with name: '%s' has been saved"),
    GETTING_VACCINATION_INFO("Getting vaccination info"),
    GOT_VACCINATION_INFO("Got vaccination with id '%s'"),
    PERSON_NOT_FOUND("Person not found"),
    SEND_QR_INFO_SUCCESS("Successfully send personName '%s' on date '%s' to create QR");

    private final String message;
}
