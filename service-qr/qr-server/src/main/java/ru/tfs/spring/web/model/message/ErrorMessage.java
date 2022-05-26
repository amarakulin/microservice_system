package ru.tfs.spring.web.model.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    QR_EXIST("The qr code for person: '%s' and date: '%s' already exist");

    private final String message;
}
