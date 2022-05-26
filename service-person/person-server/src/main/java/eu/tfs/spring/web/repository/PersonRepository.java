package eu.tfs.spring.web.repository;

import eu.tfs.spring.web.entity.IdentityDocument;
import eu.tfs.spring.web.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, UUID> {

    @EntityGraph(value = Person.GRAPH_PERSON_CONTACTS_AND_DOCUMENTS,
            type = EntityGraph.EntityGraphType.LOAD,
    attributePaths = {"identityDocuments", "contacts", "registrationAddress"})
    Optional<Person> findById(UUID uuid);

    @EntityGraph(value = Person.GRAPH_PERSON_CONTACTS_AND_DOCUMENTS,
            type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"identityDocuments", "contacts", "registrationAddress"})
    Optional<Person> findPersonByIdentityDocumentsContaining(IdentityDocument identityDocument);

    @Query("SELECT p FROM Person p WHERE p.isHidden = false " +
            "AND p.registrationAddress.region.name = :regionName " +
            "ORDER BY p.name")
    Page<Person> findAllByRegionName(@Param("regionName") String regionName, Pageable pageable);

    @EntityGraph(value = Person.GRAPH_PERSON_CONTACTS_AND_DOCUMENTS,
        type = EntityGraph.EntityGraphType.LOAD,
        attributePaths = {"identityDocuments", "contacts", "registrationAddress"})
    @Query("SELECT p FROM Person p WHERE p.id IN :personsId")
    List<Person> loadPersonsWithIds(List<UUID> personsId);
}
