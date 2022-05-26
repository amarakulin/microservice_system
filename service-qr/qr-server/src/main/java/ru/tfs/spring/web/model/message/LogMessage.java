package ru.tfs.spring.web.model.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LogMessage {
    SAVING_QR("Saving qr code for person: '%s' and date: '%s'"),
    SAVED_QR("Qr code for person: '%s' and date: '%s' has been saved"),
    GETTING_QR("Getting qr code ..."),
    GOT_QR("Qr code with id: '%s' has been gotten"),
    QR_NOT_FOUND("Qr code not found"),
    VERIFYING_QR("Verifying Qr code ..."),
    VERIFIED_QR("Qr code verified with result: '%s'");

    private final String message;
}