package eu.tfs.spring.web.service;

import eu.tfs.spring.web.entity.IdentityDocument;
import eu.tfs.spring.web.person.client.model.type.DocumentType;
import eu.tfs.spring.web.person.client.model.dto.document.IdentityDocumentRs;

import java.util.Optional;
import java.util.Set;

public interface IdentityDocumentService {
    IdentityDocumentRs getDocumentByType(DocumentType documentType, Set<IdentityDocument> documents);
    Optional<IdentityDocument> findIdentityDocumentByNumber(String number);
}
