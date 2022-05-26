package eu.tfs.spring.web.mappper;

import eu.tfs.spring.web.entity.Person;
import eu.tfs.spring.web.entity.PersonDepending;
import eu.tfs.spring.web.person.client.model.dto.document.IdentityDocumentRs;
import eu.tfs.spring.web.person.client.model.dto.person.NewPersonRq;
import eu.tfs.spring.web.person.client.model.dto.person.PersonPageFormat;
import eu.tfs.spring.web.person.client.model.dto.person.PersonRs;
import eu.tfs.spring.web.person.client.model.dto.person.UpdatePersonRq;
import org.mapstruct.*;

import java.util.Collection;
import java.util.Iterator;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {ContactMapper.class,
                AddressMapper.class,
                IdentityDocumentMapper.class}
)
public interface PersonMapper {
    @BeforeMapping
    default void cleanContactsAndDocuments(@MappingTarget Person person) {
        cleanPersonDependingRelations(person.getContacts());
        cleanPersonDependingRelations(person.getIdentityDocuments());
    }

    default <T extends PersonDepending> void  cleanPersonDependingRelations(Collection<T> collection) {
        if (collection == null) {
            return;
        }
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            PersonDepending next = iterator.next();
            next.setPerson(null);
            iterator.remove();
        }
    }

    @Mapping(target = "id", ignore = true)
    Person toEntity(NewPersonRq personRq);

    PersonRs toDtoRs(Person person);

    @Mapping(target = "id", ignore = true)
    Person updatePersonFromDtoRq(UpdatePersonRq updatePersonRq, @MappingTarget Person person);

    PersonPageFormat toPagingDto(Person person, String phoneNumber, IdentityDocumentRs identityDocument);
}
