package ru.tfs.spring.web.mapper;

import org.mapstruct.Mapper;
import ru.tfs.spring.web.medical.client.model.dto.response.AddressRs;
import ru.tfs.spring.web.model.aggregate.address.Address;

@Mapper
public interface AddressMapper {
    AddressRs toDtoRs(Address address, String city, String region);
}
