package eu.tfs.spring.web.person.client.model.dto.address;

import eu.tfs.spring.web.person.client.model.dto.region.RegionRs;

public record AddressRs (
        Long id,
        String street,
        String house,
        String apartment,
        RegionRs region
) {}
