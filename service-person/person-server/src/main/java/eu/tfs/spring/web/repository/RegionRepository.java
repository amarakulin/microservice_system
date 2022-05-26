package eu.tfs.spring.web.repository;

import eu.tfs.spring.web.entity.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {
    Optional<Region> findRegionByName(String name);
}
