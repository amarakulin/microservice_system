package ru.tfs.spring.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.tfs.spring.web.medical.client.model.dto.request.VaccinationRq;
import ru.tfs.spring.web.medical.client.model.dto.response.AddressRs;
import ru.tfs.spring.web.medical.client.model.dto.response.InfoForQrCode;
import ru.tfs.spring.web.medical.client.model.dto.response.VaccinationRs;
import ru.tfs.spring.web.model.aggregate.vaccination.Vaccination;
import ru.tfs.spring.web.model.aggregate.vaccination.VaccinationPoint;
import ru.tfs.spring.web.model.aggregate.vaccination.Vaccine;

@Mapper
public interface VaccinationMapper {

    @Mapping(target = "createAt", expression = ("java(Date.from(java.time.Instant.now()))"))
    Vaccination toEntity(VaccinationRq vaccinationRq, Integer vaccinationPointId, Integer vaccineId);

    @Mapping(target = "id", expression = "java(vaccination.id())")
    @Mapping(target = "codeVaccinationPoint", expression = "java(vaccinationPoint.codePoint())")
    @Mapping(target = "nameVaccinationPoint", expression = "java(vaccinationPoint.name())")
    @Mapping(target = "nameVaccine", expression = "java(vaccine.name())")
    VaccinationRs toDtoRs(Vaccination vaccination, VaccinationPoint vaccinationPoint, Vaccine vaccine, AddressRs addressRs);

    InfoForQrCode toQrCodeRs(Vaccination vaccination, String vaccineName);
}
