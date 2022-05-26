package eu.tfs.spring.web.service.impl;

import eu.tfs.spring.web.entity.Address;
import eu.tfs.spring.web.person.client.exception.RegionNotExistException;
import eu.tfs.spring.web.service.AddressService;
import eu.tfs.spring.web.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final RegionService regionService;

    @Override
    public void setRegionFromDbToAddress(Address address) throws RegionNotExistException {
        String regionName = address.getRegion().getName();
        address.setRegion(regionService.getRegionByName(regionName));
    }
}
