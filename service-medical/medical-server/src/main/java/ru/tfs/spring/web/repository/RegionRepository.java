package ru.tfs.spring.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tfs.spring.web.model.aggregate.address.Region;

@Repository
public interface RegionRepository extends CrudRepository<Region, Integer> {
}
