package ru.tfs.spring.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tfs.spring.web.model.entity.QrCode;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QrCodeRepository extends CrudRepository<QrCode, UUID> {
    Optional<QrCode> findQrCodeByMd5HashCode(String md5HashCode);
    List<QrCode> findAllByPersonDocumentNumber(String documentNumber);
}
