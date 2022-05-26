package ru.tfs.spring.web.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.tfs.spring.web.client.PersonClient;
import ru.tfs.spring.web.medical.client.exception.PersonClientFallbackException;
import ru.tfs.spring.web.helper.CsvHelper;
import ru.tfs.spring.web.mapper.CsvMapper;
import ru.tfs.spring.web.medical.client.model.dto.csv.CsvVaccinationRq;
import ru.tfs.spring.web.medical.client.model.dto.request.VaccinationRq;
import ru.tfs.spring.web.model.message.LogMessage;
import ru.tfs.spring.web.service.CSVService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CSVServiceImpl implements CSVService {

    private final PersonClient personClient;
    private final CsvMapper csvMapper;

    @Override
    public List<VaccinationRq> getVaccinationRqListFromCsv(MultipartFile file) throws IOException {
        log.info(LogMessage.PARSING_FILE.getMessage().formatted(file.getOriginalFilename()));
        List<CsvVaccinationRq> vaccinationRqs = CsvHelper.getValuesFromCsv(file.getInputStream(), CsvVaccinationRq.class);
        return vaccinationRqs.stream()
                .filter(rq -> personVerifyWrapper(rq.getPersonName(), rq.getPersonDocumentNumber()))
                .map(rq -> csvMapper.toVaccinationRq(rq, csvMapper.toAddressRq(rq)))
                .collect(Collectors.toList());
    }

    private Boolean personVerifyWrapper(String namePerson, String numberDocument) {
        Boolean isVerified = false;
        try {
            isVerified = personClient.verify(namePerson, numberDocument);
        } catch (PersonClientFallbackException e) {
            log.error(LogMessage.FALLBACK_PERSON_CLIENT.getMessage());
            log.error(LogMessage.DID_NOT_SAVE_PERSON.getMessage().formatted(namePerson));
        }
        return isVerified;
    }
}
