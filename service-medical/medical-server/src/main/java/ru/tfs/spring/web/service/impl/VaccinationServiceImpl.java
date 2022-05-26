package ru.tfs.spring.web.service.impl;

import com.thoughtworks.xstream.InitializationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.tfs.spring.web.medical.client.exception.InitializationDBException;
import ru.tfs.spring.web.medical.client.exception.PersonNotExistException;
import ru.tfs.spring.web.mapper.VaccinationMapper;
import ru.tfs.spring.web.medical.client.model.dto.request.VaccinationRq;
import ru.tfs.spring.web.medical.client.model.dto.response.AddressRs;
import ru.tfs.spring.web.medical.client.model.dto.response.InfoForQrCode;
import ru.tfs.spring.web.medical.client.model.dto.response.VaccinationRs;
import ru.tfs.spring.web.model.aggregate.address.Address;
import ru.tfs.spring.web.model.aggregate.vaccination.Vaccination;
import ru.tfs.spring.web.model.aggregate.vaccination.VaccinationPoint;
import ru.tfs.spring.web.model.aggregate.vaccination.Vaccine;
import ru.tfs.spring.web.model.message.ErrorMessage;
import ru.tfs.spring.web.model.message.LogMessage;
import ru.tfs.spring.web.repository.VaccinationPointRepository;
import ru.tfs.spring.web.repository.VaccinationRepository;
import ru.tfs.spring.web.repository.VaccineRepository;
import ru.tfs.spring.web.service.AddressService;
import ru.tfs.spring.web.service.CSVService;
import ru.tfs.spring.web.service.VaccinationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VaccinationServiceImpl implements VaccinationService {

    private final VaccinationRepository vaccinationRepository;
    private final VaccineRepository vaccineRepository;
    private final VaccinationPointRepository vaccinationPointRepository;
    private final VaccinationMapper vaccinationMapper;
    private final CSVService csvService;
    private final AddressService addressService;

    @Override
    public List<InfoForQrCode> getListQrCodeRsAfter(Date date) {
        List<Vaccination> vaccinations = vaccinationRepository.findByCreateAtAfter(date);
        List<InfoForQrCode> qrCodeRsList = new ArrayList<>(vaccinations.size());
        for (Vaccination vaccination : vaccinations){
            Optional<Vaccine> vaccine = vaccineRepository.findById(vaccination.vaccineId());
            vaccine.ifPresentOrElse(value ->
                            qrCodeRsList.add(vaccinationMapper.toQrCodeRs(vaccination, value.name())),
                    () -> log.error(ErrorMessage.NOT_FOUND_VACCINE.getMessage().formatted(vaccination.vaccineId())));
        }
        return qrCodeRsList;
    }

    @Override
    public VaccinationRs getVaccinationInfo(String numberDocument) throws PersonNotExistException, InitializationDBException {
        log.info(LogMessage.GETTING_VACCINATION_INFO.getMessage());
        Optional<Vaccination> vaccination = vaccinationRepository.findByPersonDocumentNumber(numberDocument);
        if (vaccination.isEmpty()) {
            log.info(LogMessage.PERSON_NOT_FOUND.getMessage());
            throw new PersonNotExistException();
        }
        VaccinationPoint vaccinationPoint = getVaccinationPointById(vaccination.get().vaccinationPointId());
        Vaccine vaccine = getVaccineById(vaccination.get().vaccineId());
        AddressRs addressRs = addressService.getAddressRsByAddressId(vaccinationPoint.addressId());
        VaccinationRs vaccinationRs = vaccinationMapper.toDtoRs(vaccination.get(), vaccinationPoint, vaccine, addressRs);
        log.info(LogMessage.GOT_VACCINATION_INFO.getMessage().formatted(vaccinationRs.id()));

        return vaccinationRs;
    }

    private VaccinationPoint getVaccinationPointById(Integer id) throws InitializationDBException {
        return vaccinationPointRepository.findById(id)
                .orElseThrow(() -> new InitializationDBException(ErrorMessage.NOT_FOUND_VACCINATION_POINT.getMessage()
                        .formatted(id)));
    }

    private Vaccine getVaccineById(Integer id) {
        return vaccineRepository.findById(id).orElseThrow(() ->
                new InitializationException(ErrorMessage.NOT_FOUND_VACCINE.getMessage().formatted(id)));
    }

    @Override
    public void saveVaccinationsFromCsv(MultipartFile file) throws IOException {
        log.info(LogMessage.SAVING_FILE.getMessage().formatted(file.getOriginalFilename()));
        List<VaccinationRq> vaccinationRqs = csvService.getVaccinationRqListFromCsv(file);
        List<Vaccination> vaccinationEntities = mapVaccinationRqsToVaccinationEntity(vaccinationRqs);
        vaccinationRepository.saveAll(vaccinationEntities);
        log.info(LogMessage.SAVED_FILE.getMessage().formatted(file.getOriginalFilename()));
    }

    private List<Vaccination> mapVaccinationRqsToVaccinationEntity(List<VaccinationRq> vaccinationRqs) {
        List<Vaccination> vaccinationEntities = new ArrayList<>(vaccinationRqs.size());
        for (VaccinationRq rq : vaccinationRqs) {
            log.info(LogMessage.SAVING_PERSON.getMessage().formatted(rq.personName()));
            Optional<Vaccination> vaccination = createVaccinationEntity(rq);
            if (vaccination.isPresent()) {
                log.info(LogMessage.SAVED_PERSON.getMessage().formatted(rq.personName()));
                vaccinationEntities.add(vaccination.get());
            }
            else {
                log.info(LogMessage.DID_NOT_SAVE_PERSON.getMessage().formatted(rq.personName()));
            }
        }
        return vaccinationEntities;
    }

    private Optional<Vaccination> createVaccinationEntity(VaccinationRq rq) {
        Optional<Vaccination> vaccinationExist = vaccinationRepository.findByPersonDocumentNumber(rq.personDocumentNumber());
        if (vaccinationExist.isEmpty()) {
            try {
                Address address = addressService.findAddressByAddressRq(rq.addressRq());
                Vaccine vaccine = findVaccine(rq.nameVaccine());
                VaccinationPoint vaccinationPoint = findVaccinationPoint(rq, address.id());
                return Optional.of(vaccinationMapper.toEntity(rq, vaccinationPoint.id(), vaccine.id()));
            } catch (InitializationDBException ex) {
                log.error(ex.getMessage());
            }
        }
        return Optional.empty();
    }

    private VaccinationPoint findVaccinationPoint(VaccinationRq vaccinationRq, Long addressId) throws InitializationDBException {
        return vaccinationPointRepository
                .findByCodePointAndAddressId(vaccinationRq.codeVaccinationPoint(), addressId)
                .orElseThrow(() ->
                        new InitializationDBException(ErrorMessage.NOT_FOUND_VACCINATION_POINT.getMessage()));
        }

    private Vaccine findVaccine(String name) throws InitializationDBException {
        return vaccineRepository.findByName(name)
                .orElseThrow(() ->
                        new InitializationDBException(ErrorMessage.NOT_FOUND_VACCINE.getMessage()));
    }
}