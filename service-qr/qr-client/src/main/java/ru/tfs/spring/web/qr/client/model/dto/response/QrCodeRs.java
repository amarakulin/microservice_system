package ru.tfs.spring.web.qr.client.model.dto.response;

import java.util.UUID;

public record QrCodeRs(
        UUID id,
        String md5HashCode
) {}
