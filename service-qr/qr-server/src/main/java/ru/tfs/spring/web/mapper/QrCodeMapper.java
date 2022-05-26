package ru.tfs.spring.web.mapper;

import org.mapstruct.Mapper;
import ru.tfs.spring.web.model.entity.QrCode;
import ru.tfs.spring.web.qr.client.model.dto.request.QrCodeRq;
import ru.tfs.spring.web.qr.client.model.dto.response.QrCodeRs;
import ru.tfs.spring.web.qr.client.model.dto.response.VerifyRs;

@Mapper
public interface QrCodeMapper {
    QrCode toEntity(QrCodeRq qrCodeRq, String md5HashCode);
    QrCodeRs toDtoRs(QrCode qrCode);
    VerifyRs toVerifyRs(QrCode qrCode, Boolean isQrExist);
}
