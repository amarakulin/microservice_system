package ru.tfs.spring.web.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import ru.tfs.spring.web.qr.client.exception.PassportNotExistException;
import ru.tfs.spring.web.mapper.QrCodeMapper;
import ru.tfs.spring.web.model.entity.QrCode;
import ru.tfs.spring.web.model.message.ErrorMessage;
import ru.tfs.spring.web.model.message.LogMessage;
import ru.tfs.spring.web.qr.client.model.dto.request.QrCodeRq;
import ru.tfs.spring.web.qr.client.model.dto.response.QrCodeRs;
import ru.tfs.spring.web.qr.client.model.dto.response.VerifyRs;
import ru.tfs.spring.web.repository.QrCodeRepository;
import ru.tfs.spring.web.service.QrCodeService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QrCodeServiceImpl implements QrCodeService {

    private final QrCodeRepository qrCodeRepository;
    private final QrCodeMapper qrCodeMapper;

    @Override
    public void save(QrCodeRq qrCodeRq) {
        log.info(LogMessage.SAVING_QR.getMessage().formatted(qrCodeRq.personName(), qrCodeRq.vaccinationDate()));
        QrCode entityToSave = qrCodeMapper.toEntity(qrCodeRq, generateMD5(qrCodeRq));
        Optional<QrCode> qrCodeFromDb = qrCodeRepository.findQrCodeByMd5HashCode(entityToSave.getMd5HashCode());
        if (qrCodeFromDb.isEmpty()) {
            qrCodeRepository.save(entityToSave);
            log.info(LogMessage.SAVED_QR.getMessage().formatted(qrCodeRq.personName(), qrCodeRq.vaccinationDate()));
        }
        else {
            log.error(ErrorMessage.QR_EXIST.getMessage()
                    .formatted(qrCodeFromDb.get().getPersonName(), qrCodeFromDb.get().getVaccinationDate()));
        }
    }

    private String generateMD5(QrCodeRq qrCodeRq) {
        return DigestUtils.md5Hex(qrCodeRq.toString());
    }

    @Override
    public QrCodeRs getQrCodeRsByPassport(String passport) throws PassportNotExistException {
        log.info(LogMessage.GETTING_QR.getMessage());
        List<QrCode> allByPassport = qrCodeRepository.findAllByPersonDocumentNumber(passport);
        if (allByPassport.isEmpty()) {
            log.info(LogMessage.QR_NOT_FOUND.getMessage());
            throw new PassportNotExistException(passport);
        }
        QrCode qrCode = allByPassport.stream()
                .reduce(allByPassport.get(0),
                        (current, old) -> getQrCodeByLatestDate(current, old));
        log.info(LogMessage.GOT_QR.getMessage().formatted(qrCode.getId()));
        return qrCodeMapper.toDtoRs(qrCode);
    }

    private QrCode getQrCodeByLatestDate(QrCode first, QrCode second) {
        if (first == null || second == null) {
            return first == null ? second : first;
        }
        return first.getVaccinationDate()
                .compareTo(second.getVaccinationDate()) >= 0 ? first : second;
    }

    @Override
    public VerifyRs verifyQrCodeByMd5Hash(String code) {
        log.info(LogMessage.VERIFYING_QR.getMessage());
        QrCode qrCode = qrCodeRepository.findQrCodeByMd5HashCode(code)
                .orElse(null);
        Boolean isQrExist = !(qrCode == null);
        log.info(LogMessage.VERIFYING_QR.getMessage().formatted(isQrExist));
        return qrCodeMapper.toVerifyRs(qrCode, isQrExist);
    }
}
