package ru.tfs.spring.web.qr.client.model.dto.response;

import java.util.Date;
import java.util.UUID;

public record VerifyRs (
        UUID id,
        Date vaccinationDate,
        String personName,
        Boolean isQrExist
){
}
