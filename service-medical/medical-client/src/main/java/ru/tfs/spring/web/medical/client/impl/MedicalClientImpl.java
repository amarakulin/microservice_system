package ru.tfs.spring.web.medical.client.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tfs.spring.web.medical.client.MedicalClient;
import ru.tfs.spring.web.medical.client.exception.MedicalClientFallbackException;
import ru.tfs.spring.web.medical.client.exception.PersonNotExistException;
import ru.tfs.spring.web.medical.client.model.dto.response.VaccinationRs;

public class MedicalClientImpl implements MedicalClient {

    private WebClient webClient;

    public MedicalClientImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<VaccinationRs> getVaccinationByPassport(String passport) throws PersonNotExistException, MedicalClientFallbackException {
        Mono<VaccinationRs> vaccinationRsMono = webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/vaccination")
                        .queryParam("document", passport)
                        .build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response ->
                        Mono.error(new PersonNotExistException()))
                .onStatus(HttpStatus::is5xxServerError, response ->
                        Mono.error(new MedicalClientFallbackException()))
                .bodyToMono(VaccinationRs.class);
        return Mono.from(vaccinationRsMono);
    }
}
