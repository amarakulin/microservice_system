package ru.tfs.spring.web.service;

import eu.tfs.spring.web.person.client.exception.PersonClientFallbackException;
import eu.tfs.spring.web.person.client.exception.PersonNotFoundException;
import reactor.core.publisher.Mono;
import ru.tfs.spring.web.medical.client.exception.MedicalClientFallbackException;
import ru.tfs.spring.web.medical.client.exception.PersonNotExistException;
import ru.tfs.spring.web.model.dto.response.InfoRs;
import ru.tfs.spring.web.qr.client.exception.PassportNotExistException;
import ru.tfs.spring.web.qr.client.exception.QrServiceFallbackException;

public interface InfoService {
    Mono<InfoRs> getInfoByPassport(String passportNumber) throws PersonClientFallbackException, PersonNotFoundException, PersonNotExistException, MedicalClientFallbackException, PassportNotExistException, QrServiceFallbackException;
}
