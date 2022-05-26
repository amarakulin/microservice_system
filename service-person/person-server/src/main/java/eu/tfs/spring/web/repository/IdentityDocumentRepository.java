package eu.tfs.spring.web.repository;

import eu.tfs.spring.web.entity.IdentityDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdentityDocumentRepository extends CrudRepository<IdentityDocument, Long> {
    Optional<IdentityDocument> findByNumber(String number);
}
