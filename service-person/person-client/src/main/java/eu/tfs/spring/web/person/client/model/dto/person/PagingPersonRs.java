package eu.tfs.spring.web.person.client.model.dto.person;

import java.util.Set;

public record PagingPersonRs (
        Set<PersonPageFormat> personPageFormat,
        Integer page,
        Integer size
) {}
