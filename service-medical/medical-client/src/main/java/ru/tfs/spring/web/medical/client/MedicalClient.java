package ru.tfs.spring.web.medical.client;

import reactor.core.publisher.Mono;
import ru.tfs.spring.web.medical.client.exception.MedicalClientFallbackException;
import ru.tfs.spring.web.medical.client.exception.PersonNotExistException;
import ru.tfs.spring.web.medical.client.model.dto.response.VaccinationRs;

public interface MedicalClient {
    Mono<VaccinationRs> getVaccinationByPassport(String passport) throws PersonNotExistException, MedicalClientFallbackException;
}
