package ru.tfs.spring.web.model.dto.response;

import eu.tfs.spring.web.person.client.model.dto.person.PersonRs;
import ru.tfs.spring.web.medical.client.model.dto.response.VaccinationRs;
import ru.tfs.spring.web.qr.client.model.dto.response.QrCodeRs;

public record InfoRs(
        PersonRs personRs,
        QrCodeRs qrCodeRs,
        VaccinationRs vaccinationRs
) {}
