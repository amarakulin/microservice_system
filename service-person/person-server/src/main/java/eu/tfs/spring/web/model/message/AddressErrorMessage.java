package eu.tfs.spring.web.model.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AddressErrorMessage {
    REGION_NOT_EXIST("Region with name '%s' not exist");

    private final String message;
}
