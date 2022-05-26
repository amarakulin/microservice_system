package ru.tfs.spring.web.controller;

import eu.tfs.spring.web.person.client.exception.PersonClientFallbackException;
import eu.tfs.spring.web.person.client.exception.PersonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.tfs.spring.web.medical.client.exception.MedicalClientFallbackException;
import ru.tfs.spring.web.medical.client.exception.PersonNotExistException;
import ru.tfs.spring.web.model.dto.response.InfoRs;
import ru.tfs.spring.web.qr.client.exception.PassportNotExistException;
import ru.tfs.spring.web.qr.client.exception.QrServiceFallbackException;
import ru.tfs.spring.web.service.InfoService;

@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {

    private final InfoService infoService;

    @GetMapping("/{passport}")
    public Mono<InfoRs> getInfoByPassport(@PathVariable String passport) throws PersonClientFallbackException, PersonNotFoundException, PersonNotExistException, MedicalClientFallbackException, PassportNotExistException, QrServiceFallbackException {
        Mono<InfoRs> infoRsMono = infoService.getInfoByPassport(passport);
        return infoRsMono;
    }
}
