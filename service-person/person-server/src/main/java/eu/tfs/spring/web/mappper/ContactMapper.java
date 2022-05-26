package eu.tfs.spring.web.mappper;

import eu.tfs.spring.web.entity.Contact;
import eu.tfs.spring.web.person.client.model.dto.contact.ContactRs;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    Contact toEntity(String value);
    ContactRs toDto(Contact contact);
}
