package eu.tfs.spring.web.mappper;

import eu.tfs.spring.web.entity.Address;
import eu.tfs.spring.web.person.client.model.dto.address.AddressRq;
import eu.tfs.spring.web.person.client.model.dto.address.AddressRs;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {RegionMapper.class}
)
public interface AddressMapper {

    Address toEntity(AddressRq addressRq);
    AddressRs toDtoRs(Address address);
}