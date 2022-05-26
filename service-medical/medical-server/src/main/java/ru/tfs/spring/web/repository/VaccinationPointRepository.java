package ru.tfs.spring.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tfs.spring.web.model.aggregate.vaccination.VaccinationPoint;

import java.util.Optional;

@Repository
public interface VaccinationPointRepository extends CrudRepository<VaccinationPoint, Integer> {
    Optional<VaccinationPoint> findByCodePointAndAddressId(String codePoint, Long addressId);
}
