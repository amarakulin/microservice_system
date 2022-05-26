package eu.tfs.spring.web.mappper;

import eu.tfs.spring.web.entity.IdentityDocument;
import eu.tfs.spring.web.person.client.model.dto.document.IdentityDocumentRq;
import eu.tfs.spring.web.person.client.model.dto.document.IdentityDocumentRs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IdentityDocumentMapper {
    IdentityDocument toEntity(IdentityDocumentRq identityDocumentRq);
    @Mapping(target = "documentId", source = "id")
    IdentityDocumentRs toDtoRs(IdentityDocument identityDocument);
}