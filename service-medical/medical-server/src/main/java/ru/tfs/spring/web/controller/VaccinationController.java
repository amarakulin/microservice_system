package ru.tfs.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.tfs.spring.web.medical.client.exception.InitializationDBException;
import ru.tfs.spring.web.medical.client.exception.PersonNotExistException;
import ru.tfs.spring.web.medical.client.model.dto.response.VaccinationRs;
import ru.tfs.spring.web.service.VaccinationService;

import java.io.IOException;

@RestController
@RequestMapping("/vaccination")
@RequiredArgsConstructor
public class VaccinationController {

    private final static ResponseEntity<String> fileSavedRs =
            new ResponseEntity("File saved successfully", HttpStatus.OK);
    private final VaccinationService vaccinationService;

    @GetMapping
    public VaccinationRs getVaccinationInfo(@RequestParam(name = "document") String numberDocument) throws PersonNotExistException, InitializationDBException {
        return vaccinationService.getVaccinationInfo(numberDocument);
    }

    @PostMapping("/file/process-file")
    public ResponseEntity<String> saveVaccinationInfo(@RequestParam("file") MultipartFile csvFile) throws IOException {
        vaccinationService.saveVaccinationsFromCsv(csvFile);
        return fileSavedRs;
    }
}
