package eu.tfs.spring.web.model.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogMessage {
    SAVE_PERSON("Saving person with name '%s'"),
    SAVED_PERSON("Person with name '%s' has been saved"),
    UPDATE_PERSON("Updating person with id '%s'"),
    UPDATED_PERSON("Person with id '%s' has been updated"),
    GETTING_BY_PASSPORT("Getting person by passport ..."),
    GOT_BY_PASSPORT("Person with id '%s' has been gotten by passport"),
    GETTING_BY_ID("Getting person with id '%s'"),
    GOT_BY_ID("Got person with id '%s'"),
    VERIFYING("Verifying person with name '%s'"),
    VERIFIED("Person with name '%s' has been verified with result: '%s'"),
    GETTING_PERSONS("Getting persons in region '%s' on page number '%s'"),
    GOT_PERSONS("Got '%s' persons");

    private final String message;
}
