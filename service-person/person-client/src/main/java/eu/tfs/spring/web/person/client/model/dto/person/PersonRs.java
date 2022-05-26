package eu.tfs.spring.web.person.client.model.dto.person;

import eu.tfs.spring.web.person.client.model.dto.address.AddressRs;
import eu.tfs.spring.web.person.client.model.dto.contact.ContactRs;
import eu.tfs.spring.web.person.client.model.dto.document.IdentityDocumentRs;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public record PersonRs (
        UUID id,
        String name,
        Date dateOfBirth,
        AddressRs registrationAddress,
        Set<IdentityDocumentRs> identityDocuments,
        Set<ContactRs> contacts,
        Set<AddressRs> addresses
) {}
