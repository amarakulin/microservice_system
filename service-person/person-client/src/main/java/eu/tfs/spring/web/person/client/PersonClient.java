package eu.tfs.spring.web.person.client;

import eu.tfs.spring.web.person.client.exception.PersonClientFallbackException;
import eu.tfs.spring.web.person.client.exception.PersonNotFoundException;
import eu.tfs.spring.web.person.client.model.dto.person.PersonRs;
import reactor.core.publisher.Mono;

public interface PersonClient {

    Mono<PersonRs> getPersonByPassport(String passport) throws PersonNotFoundException, PersonClientFallbackException;
}
