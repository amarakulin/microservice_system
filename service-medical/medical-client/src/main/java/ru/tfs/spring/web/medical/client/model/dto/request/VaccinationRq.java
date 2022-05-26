package ru.tfs.spring.web.medical.client.model.dto.request;

import java.util.Date;

public record VaccinationRq (
        String personName,
        String personDocumentNumber,
        String ampouleNumber,
        Date vaccinationDate,
        String nameVaccine,
        String codeVaccinationPoint,
        String nameVaccinationPoint,
        AddressRq addressRq
) {}