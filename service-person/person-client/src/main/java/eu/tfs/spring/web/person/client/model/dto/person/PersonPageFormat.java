package eu.tfs.spring.web.person.client.model.dto.person;

import eu.tfs.spring.web.person.client.model.dto.address.AddressRs;
import eu.tfs.spring.web.person.client.model.dto.document.IdentityDocumentRs;

import java.util.Date;
import java.util.UUID;

public record PersonPageFormat(
        UUID id,
        String name,
        Date dateOfBirth,
        String phoneNumber,
        IdentityDocumentRs identityDocument,
        AddressRs registrationAddress
) {}
