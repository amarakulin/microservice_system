package ru.tfs.spring.web.medical.client.model.dto.response;

import java.util.Date;

public record InfoForQrCode(
        String personName,
        String personDocumentNumber,
        String vaccineName,
        Date vaccinationDate
) {}
