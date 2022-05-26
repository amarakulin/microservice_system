package ru.tfs.spring.web.service.impl;

import eu.tfs.spring.web.person.client.PersonClient;
import eu.tfs.spring.web.person.client.exception.PersonClientFallbackException;
import eu.tfs.spring.web.person.client.exception.PersonNotFoundException;
import eu.tfs.spring.web.person.client.model.dto.person.PersonRs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.tfs.spring.web.medical.client.MedicalClient;
import ru.tfs.spring.web.medical.client.exception.MedicalClientFallbackException;
import ru.tfs.spring.web.medical.client.exception.PersonNotExistException;
import ru.tfs.spring.web.medical.client.model.dto.response.VaccinationRs;
import ru.tfs.spring.web.model.dto.response.InfoRs;
import ru.tfs.spring.web.qr.client.QrClient;
import ru.tfs.spring.web.qr.client.exception.PassportNotExistException;
import ru.tfs.spring.web.qr.client.exception.QrServiceFallbackException;
import ru.tfs.spring.web.qr.client.model.dto.response.QrCodeRs;
import ru.tfs.spring.web.service.InfoService;

@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {

    private final PersonClient personClient;
    private final QrClient qrClient;
    private final MedicalClient medicalClient;

    @Override
    public Mono<InfoRs> getInfoByPassport(String passportNumber) throws PersonClientFallbackException, PersonNotFoundException, PersonNotExistException, MedicalClientFallbackException, PassportNotExistException, QrServiceFallbackException {

        Mono<PersonRs> personRsMono = personClient.getPersonByPassport(passportNumber);
        Mono<QrCodeRs> qrCodeRsMono = qrClient.getQrByPassport(passportNumber);
        Mono<VaccinationRs> vaccinationRsMono = medicalClient.getVaccinationByPassport(passportNumber);
        return Mono.zip(personRsMono, qrCodeRsMono, vaccinationRsMono)
                .map(data -> new InfoRs(data.getT1(), data.getT2(), data.getT3()));
    }
}
