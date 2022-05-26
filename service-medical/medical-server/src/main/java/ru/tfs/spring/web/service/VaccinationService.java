package ru.tfs.spring.web.service;

import org.springframework.web.multipart.MultipartFile;
import ru.tfs.spring.web.medical.client.exception.InitializationDBException;
import ru.tfs.spring.web.medical.client.exception.PersonNotExistException;
import ru.tfs.spring.web.medical.client.model.dto.response.InfoForQrCode;
import ru.tfs.spring.web.medical.client.model.dto.response.VaccinationRs;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface VaccinationService {
    VaccinationRs getVaccinationInfo(String numberDocument) throws PersonNotExistException, InitializationDBException;
    void saveVaccinationsFromCsv(MultipartFile file) throws IOException;
    List<InfoForQrCode> getListQrCodeRsAfter(Date date);
}
