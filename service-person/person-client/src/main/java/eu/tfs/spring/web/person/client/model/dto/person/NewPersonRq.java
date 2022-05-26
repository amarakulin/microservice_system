package eu.tfs.spring.web.person.client.model.dto.person;

import eu.tfs.spring.web.person.client.model.dto.address.AddressRq;
import eu.tfs.spring.web.person.client.model.dto.document.IdentityDocumentRq;
import eu.tfs.spring.web.person.client.model.type.DocumentType;

import java.util.Date;
import java.util.List;

public record NewPersonRq(
       String name,
       AddressRq registrationAddress,
       Date dateOfBirth,
       DocumentType primaryDocument,
       List<AddressRq> addresses,
       List<String> contacts,
       List<IdentityDocumentRq> identityDocuments
) {}
