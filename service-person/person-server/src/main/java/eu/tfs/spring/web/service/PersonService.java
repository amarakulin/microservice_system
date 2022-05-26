package eu.tfs.spring.web.service;

import eu.tfs.spring.web.person.client.exception.PersonExistException;
import eu.tfs.spring.web.person.client.exception.PersonNotFoundException;
import eu.tfs.spring.web.person.client.exception.RegionNotExistException;
import eu.tfs.spring.web.person.client.exception.WrongValueParameterException;
import eu.tfs.spring.web.person.client.model.dto.person.NewPersonRq;
import eu.tfs.spring.web.person.client.model.dto.person.PersonPageFormat;
import eu.tfs.spring.web.person.client.model.dto.person.PersonRs;
import eu.tfs.spring.web.person.client.model.dto.person.UpdatePersonRq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PersonService {
    UUID savePerson(NewPersonRq personRq) throws PersonExistException, RegionNotExistException;
    UUID updatePerson(UpdatePersonRq personRq) throws PersonNotFoundException;
    PersonRs getPersonById(UUID id) throws PersonNotFoundException;
    PersonRs getPersonByPassport(String passportNumber) throws PersonNotFoundException;
    Page<PersonPageFormat> getPersons(String regionName, Pageable pageable) throws WrongValueParameterException;
    Boolean verifyPerson(String name, String passport);
}
