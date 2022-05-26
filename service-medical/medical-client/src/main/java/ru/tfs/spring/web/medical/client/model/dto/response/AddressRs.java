package ru.tfs.spring.web.medical.client.model.dto.response;

public record AddressRs (
        Long id,
        String house,
        String street,
        String city,
        String region
) {}
