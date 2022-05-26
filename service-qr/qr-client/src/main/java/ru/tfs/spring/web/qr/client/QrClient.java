package ru.tfs.spring.web.qr.client;

import reactor.core.publisher.Mono;
import ru.tfs.spring.web.qr.client.exception.PassportNotExistException;
import ru.tfs.spring.web.qr.client.exception.QrServiceFallbackException;
import ru.tfs.spring.web.qr.client.model.dto.response.QrCodeRs;

public interface QrClient {
    Mono<QrCodeRs> getQrByPassport(String passport) throws PassportNotExistException, QrServiceFallbackException;
}
