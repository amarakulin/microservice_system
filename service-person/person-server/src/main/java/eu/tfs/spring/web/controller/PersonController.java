package eu.tfs.spring.web.controller;

import eu.tfs.spring.web.person.client.exception.PersonExistException;
import eu.tfs.spring.web.person.client.exception.PersonNotFoundException;
import eu.tfs.spring.web.person.client.exception.RegionNotExistException;
import eu.tfs.spring.web.person.client.exception.WrongValueParameterException;
import eu.tfs.spring.web.person.client.model.dto.person.NewPersonRq;
import eu.tfs.spring.web.person.client.model.dto.person.PersonPageFormat;
import eu.tfs.spring.web.person.client.model.dto.person.PersonRs;
import eu.tfs.spring.web.person.client.model.dto.person.UpdatePersonRq;
import eu.tfs.spring.web.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public UUID createPerson(@Valid @RequestBody NewPersonRq personRq) throws PersonExistException, RegionNotExistException {
        return personService.savePerson(personRq);
    }

    @PutMapping
    public UUID updatePerson(@Valid @RequestBody UpdatePersonRq updatePersonRq) throws PersonNotFoundException {
        return personService.updatePerson(updatePersonRq);
    }

    @GetMapping("/{id}")
    public PersonRs getPersonById(@PathVariable UUID id) throws PersonNotFoundException {
        return personService.getPersonById(id);
    }

    @GetMapping("/passport")
    public PersonRs getPersonByPassport(@RequestParam String passport) throws PersonNotFoundException {
        return personService.getPersonByPassport(passport);
    }

    @GetMapping
    public Page<PersonPageFormat> getPersons(@RequestParam(required = false) String region,
                                             Pageable pageable) throws WrongValueParameterException {
        return personService.getPersons(region, pageable);
    }

    @GetMapping("/verify")
    public Boolean verifyPerson(@RequestParam String name, @RequestParam String passport) {
        return personService.verifyPerson(name, passport);
    }
}
