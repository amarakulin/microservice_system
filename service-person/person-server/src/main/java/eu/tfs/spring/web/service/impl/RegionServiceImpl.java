package eu.tfs.spring.web.service.impl;

import eu.tfs.spring.web.entity.Region;
import eu.tfs.spring.web.person.client.exception.RegionNotExistException;
import eu.tfs.spring.web.repository.RegionRepository;
import eu.tfs.spring.web.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Override
    public Region getRegionByName(String name) throws RegionNotExistException {
        return regionRepository.findRegionByName(name)
                .orElseThrow(() -> new RegionNotExistException(name));
    }
}
