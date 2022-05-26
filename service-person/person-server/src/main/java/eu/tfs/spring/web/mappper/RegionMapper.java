package eu.tfs.spring.web.mappper;

import eu.tfs.spring.web.entity.Region;
import eu.tfs.spring.web.person.client.model.dto.region.RegionRs;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {AddressMapper.class}
)
public interface RegionMapper {

    Region toEntity(String name);
    RegionRs toDto(Region region);
}
