package ru.tfs.spring.web.qr.client.model.dto.request;

import java.util.Date;

public record QrCodeRq (
    String personName,
    String personDocumentNumber,
    String vaccineName,
    Date vaccinationDate) {

    @Override
    public String toString() {
        return personName + '_' +
                personDocumentNumber + '_' +
                vaccineName + '_' +
                vaccinationDate;
    }
}
