package eu.tfs.spring.web.person.client.model.dto.document;

import eu.tfs.spring.web.person.client.model.type.DocumentType;

public record IdentityDocumentRs (
    Long documentId,
    DocumentType type,
    String number
) {}
