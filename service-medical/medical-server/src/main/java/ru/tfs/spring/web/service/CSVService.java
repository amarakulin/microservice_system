package ru.tfs.spring.web.service;

import org.springframework.web.multipart.MultipartFile;
import ru.tfs.spring.web.medical.client.model.dto.request.VaccinationRq;

import java.io.IOException;
import java.util.List;

public interface CSVService {
    List<VaccinationRq> getVaccinationRqListFromCsv(MultipartFile file) throws IOException;
}
