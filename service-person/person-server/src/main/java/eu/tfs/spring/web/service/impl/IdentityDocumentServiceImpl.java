package eu.tfs.spring.web.service.impl;

import eu.tfs.spring.web.entity.IdentityDocument;
import eu.tfs.spring.web.person.client.model.type.DocumentType;
import eu.tfs.spring.web.mappper.IdentityDocumentMapper;
import eu.tfs.spring.web.person.client.model.dto.document.IdentityDocumentRs;
import eu.tfs.spring.web.repository.IdentityDocumentRepository;
import eu.tfs.spring.web.service.IdentityDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class IdentityDocumentServiceImpl implements IdentityDocumentService {

    private final IdentityDocumentMapper identityDocumentMapper;
    private final IdentityDocumentRepository identityDocumentRepository;

    @Override
    public IdentityDocumentRs getDocumentByType(DocumentType documentType, Set<IdentityDocument> documents) {
        IdentityDocument identityDocument = documents.stream()
                .filter(doc -> documentType.equals(doc.getType()))
                .findFirst()
                .orElse(null);
        return identityDocumentMapper.toDtoRs(identityDocument);
    }

    @Override
    public Optional<IdentityDocument> findIdentityDocumentByNumber(String number) {
        return identityDocumentRepository.findByNumber(number);
    }
}
