package ru.tfs.spring.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tfs.spring.web.model.aggregate.vaccination.Vaccination;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccinationRepository extends CrudRepository<Vaccination, Long> {
    Optional<Vaccination> findByPersonDocumentNumber(String numberDocument);
    List<Vaccination> findByCreateAtAfter(Date date);
}
