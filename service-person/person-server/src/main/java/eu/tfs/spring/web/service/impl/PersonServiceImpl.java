package eu.tfs.spring.web.service.impl;

import eu.tfs.spring.web.entity.Address;
import eu.tfs.spring.web.entity.IdentityDocument;
import eu.tfs.spring.web.entity.Person;
import eu.tfs.spring.web.entity.Region;
import eu.tfs.spring.web.person.client.exception.RegionNotExistException;
import eu.tfs.spring.web.model.message.LogMessage;
import eu.tfs.spring.web.person.client.model.type.DocumentType;
import eu.tfs.spring.web.person.client.exception.PersonExistException;
import eu.tfs.spring.web.person.client.exception.PersonNotFoundException;
import eu.tfs.spring.web.person.client.exception.WrongValueParameterException;
import eu.tfs.spring.web.mappper.PersonMapper;
import eu.tfs.spring.web.person.client.model.dto.document.IdentityDocumentRq;
import eu.tfs.spring.web.person.client.model.dto.person.NewPersonRq;
import eu.tfs.spring.web.person.client.model.dto.person.PersonPageFormat;
import eu.tfs.spring.web.person.client.model.dto.person.PersonRs;
import eu.tfs.spring.web.person.client.model.dto.person.UpdatePersonRq;
import eu.tfs.spring.web.repository.PersonRepository;
import eu.tfs.spring.web.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final ContactService contactService;
    private final IdentityDocumentService identityDocumentService;
    private final RegionService regionService;
    private final AddressService addressService;

    @Override
    public UUID savePerson(NewPersonRq personRq) throws PersonExistException, RegionNotExistException {
        log.info(LogMessage.SAVE_PERSON.getMessage().formatted(personRq.name()));
        validateNewPerson(personRq);
        Person entity = getPersonEntity(personRq);
        Person savedPerson = personRepository.save(entity);
        log.info(LogMessage.SAVED_PERSON.getMessage().formatted(personRq.name()));
        return savedPerson.getId();
    }

    private Person getPersonEntity(NewPersonRq personRq) throws RegionNotExistException {
        Person entity = personMapper.toEntity(personRq);
        updateAddressesWithRegionFromDb(entity);
        return entity;
    }

    private void updateAddressesWithRegionFromDb(final Person entity) throws RegionNotExistException {
        Set<Address> addresses = entity.getAddresses();
        for (Address address : addresses) {
            addressService.setRegionFromDbToAddress(address);
        }
        addressService.setRegionFromDbToAddress(entity.getRegistrationAddress());
    }

    private void validateNewPerson(NewPersonRq personRq) throws PersonExistException {
        if (isPersonExist(personRq)) {
            throw new PersonExistException();
        }
    }

    private Boolean isPersonExist(NewPersonRq personRq) {
        Optional<IdentityDocumentRq> passport = personRq.identityDocuments().stream()
                .filter(doc -> DocumentType.PASSPORT.equals(doc.type()))
                .findFirst();
        if (passport.isEmpty()) {
            return false;
        }
        return identityDocumentService.findIdentityDocumentByNumber(passport.get().number())
                .isPresent();
    }

    @Override
    @Transactional
    public UUID updatePerson(UpdatePersonRq personRq) throws PersonNotFoundException {
        log.info(LogMessage.UPDATE_PERSON.getMessage().formatted(personRq.id()));
        Person person = personRepository.findById(personRq.id())
                .orElseThrow(PersonNotFoundException::new);
        Person updatedPerson = personMapper.updatePersonFromDtoRq(personRq, person);
        log.info(LogMessage.UPDATED_PERSON.getMessage().formatted(personRq.id()));
        return updatedPerson.getId();
    }

    @Override
    public PersonRs getPersonByPassport(String passportNumber) throws PersonNotFoundException {
        log.info(LogMessage.GETTING_BY_PASSPORT.getMessage());
        IdentityDocument passport = identityDocumentService.findIdentityDocumentByNumber(passportNumber)
                .orElseThrow(PersonNotFoundException::new);
        Person person = personRepository.findPersonByIdentityDocumentsContaining(passport)
                .orElseThrow(PersonNotFoundException::new);
        log.info(LogMessage.GOT_BY_PASSPORT.getMessage().formatted(person.getId()));
        return personMapper.toDtoRs(person);
    }

    @Override
    public PersonRs getPersonById(UUID id) throws PersonNotFoundException {
        log.info(LogMessage.GETTING_BY_ID.getMessage().formatted(id));
        Person person = personRepository.findById(id)
                .orElseThrow(PersonNotFoundException::new);
        log.info(LogMessage.GOT_BY_ID.getMessage().formatted(person.getId()));
        return personMapper.toDtoRs(person);
    }

    @Override
    public Page<PersonPageFormat> getPersons(String regionName, Pageable pageable) throws WrongValueParameterException {
        log.info(LogMessage.GETTING_PERSONS.getMessage().formatted(regionName, pageable.getPageNumber()));
        Page<Person> pagePersons = getPagePersons(pageable, regionName);
        return mapPersonToPageFormat(pagePersons);
    }

    private Page<Person> getPagePersons(Pageable pageable, String regionName) {
        Page<Person> personPage;
        try {
            Region region = regionService.getRegionByName(regionName);
            personPage = personRepository.findAllByRegionName(region.getName(), pageable);
        } catch (RegionNotExistException ex) {
            personPage = personRepository.findAll(pageable);
        }
        List<UUID> personsId = personPage.stream()
                .map(Person::getId)
                .collect(Collectors.toList());
        List<Person> loadPersons = personRepository.loadPersonsWithIds(personsId);
        return new PageImpl<>(loadPersons, pageable, personPage.getTotalElements());
    }

    private Page<PersonPageFormat> mapPersonToPageFormat(Page<Person> pagePersons) {
        Page<PersonPageFormat> persons =  pagePersons
                .map(person -> personMapper.toPagingDto(
                        person,
                        contactService.getPhone(person.getContacts()),
                        identityDocumentService.getDocumentByType(person.getPrimaryDocument(), person.getIdentityDocuments())));
        log.info(LogMessage.GOT_PERSONS.getMessage().formatted(persons.getTotalElements()));
        return persons;
    }

    @Override
    @Transactional
    public Boolean verifyPerson(String name, String passport) {
        Boolean result = false;
        log.info(LogMessage.VERIFYING.getMessage().formatted(name));
        Optional<IdentityDocument> document = identityDocumentService.findIdentityDocumentByNumber(passport);
        if (document.isPresent() && name != null) {
            Person person = document.get().getPerson();
            result = name.equals(person.getName());

        }
        log.info(LogMessage.VERIFIED.getMessage().formatted(name, result));
        return result;
    }
}
