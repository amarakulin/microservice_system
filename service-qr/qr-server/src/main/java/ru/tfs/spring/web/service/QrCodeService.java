package ru.tfs.spring.web.service;

import ru.tfs.spring.web.qr.client.exception.PassportNotExistException;
import ru.tfs.spring.web.qr.client.model.dto.request.QrCodeRq;
import ru.tfs.spring.web.qr.client.model.dto.response.QrCodeRs;
import ru.tfs.spring.web.qr.client.model.dto.response.VerifyRs;

public interface QrCodeService {
    void save(QrCodeRq qrCodeRq);
    QrCodeRs getQrCodeRsByPassport(String passport) throws PassportNotExistException;
    VerifyRs verifyQrCodeByMd5Hash(String code);
}
