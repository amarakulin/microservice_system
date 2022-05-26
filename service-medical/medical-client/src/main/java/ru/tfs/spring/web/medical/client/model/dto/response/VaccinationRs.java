package ru.tfs.spring.web.medical.client.model.dto.response;

import java.util.Date;

public record VaccinationRs (
        Long id,
        String personName,
        String personDocumentNumber,
        String ampouleNumber,
        Date vaccinationDate,
        String nameVaccine,
        String codeVaccinationPoint,
        String nameVaccinationPoint,
        AddressRs addressRs
) {}
