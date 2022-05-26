package ru.tfs.spring.web.mapper;

import org.mapstruct.Mapper;
import ru.tfs.spring.web.medical.client.model.dto.csv.CsvVaccinationRq;
import ru.tfs.spring.web.medical.client.model.dto.request.AddressRq;
import ru.tfs.spring.web.medical.client.model.dto.request.VaccinationRq;

@Mapper(uses = {
        AddressMapper.class
})
public interface CsvMapper {
    VaccinationRq toVaccinationRq(CsvVaccinationRq csvVaccinationRq, AddressRq addressRq);
    AddressRq toAddressRq(CsvVaccinationRq csvVaccinationRq);
}
