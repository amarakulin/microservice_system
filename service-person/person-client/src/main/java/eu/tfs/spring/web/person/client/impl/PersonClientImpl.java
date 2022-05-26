package eu.tfs.spring.web.person.client.impl;

import eu.tfs.spring.web.person.client.PersonClient;
import eu.tfs.spring.web.person.client.exception.PersonClientFallbackException;
import eu.tfs.spring.web.person.client.exception.PersonNotFoundException;
import eu.tfs.spring.web.person.client.model.dto.person.PersonRs;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class PersonClientImpl implements PersonClient {

    private WebClient webClient;

    public PersonClientImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<PersonRs> getPersonByPassport(String passport) throws PersonNotFoundException, PersonClientFallbackException{
        Mono<PersonRs> personRsMono = webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/person/passport")
                        .queryParam("passport", passport)
                        .build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response ->
                        Mono.error(new PersonNotFoundException()))
                .onStatus(HttpStatus::is5xxServerError, response ->
                        Mono.error(new PersonClientFallbackException()))
                .bodyToMono(PersonRs.class);

        return Mono.from(personRsMono);
    }
}
