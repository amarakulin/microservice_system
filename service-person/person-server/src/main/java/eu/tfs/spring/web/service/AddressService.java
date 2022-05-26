package eu.tfs.spring.web.service;

import eu.tfs.spring.web.entity.Address;
import eu.tfs.spring.web.person.client.exception.RegionNotExistException;

public interface AddressService {
    void setRegionFromDbToAddress(final Address address) throws RegionNotExistException;
}
