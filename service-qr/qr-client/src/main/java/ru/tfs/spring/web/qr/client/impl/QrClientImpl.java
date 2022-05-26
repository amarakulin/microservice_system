package ru.tfs.spring.web.qr.client.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tfs.spring.web.qr.client.QrClient;
import ru.tfs.spring.web.qr.client.exception.PassportNotExistException;
import ru.tfs.spring.web.qr.client.exception.QrServiceFallbackException;
import ru.tfs.spring.web.qr.client.model.dto.response.QrCodeRs;

public class QrClientImpl implements QrClient {

    private WebClient webClient;

    public QrClientImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<QrCodeRs> getQrByPassport(String passport) {
        Mono<QrCodeRs> qrCodeRsMono = webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/qr/%s".formatted(passport))
                        .build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response ->
                        Mono.error(new PassportNotExistException(passport)))
                .onStatus(HttpStatus::is5xxServerError, response ->
                        Mono.error(new QrServiceFallbackException()))
                .bodyToMono(QrCodeRs.class);
        return Mono.from(qrCodeRsMono);
    }
}
