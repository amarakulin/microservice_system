package ru.tfs.spring.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tfs.spring.web.model.aggregate.vaccination.Vaccine;

import java.util.Optional;

@Repository
public interface VaccineRepository extends CrudRepository<Vaccine, Integer> {
    Optional<Vaccine> findByName(String name);
}
