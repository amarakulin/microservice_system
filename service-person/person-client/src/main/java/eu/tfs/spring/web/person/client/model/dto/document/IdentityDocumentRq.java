package eu.tfs.spring.web.person.client.model.dto.document;

import eu.tfs.spring.web.person.client.model.type.DocumentType;

public record IdentityDocumentRq (
    DocumentType type,
    String number
) {}
