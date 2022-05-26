package eu.tfs.spring.web.service;

import eu.tfs.spring.web.entity.Region;
import eu.tfs.spring.web.person.client.exception.RegionNotExistException;

public interface RegionService {
    Region getRegionByName(String name) throws RegionNotExistException;
}
