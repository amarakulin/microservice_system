package ru.tfs.spring.web.medical.client.model.dto.request;

public record AddressRq (
        String house,
        String street,
        String city,
        String region
) {}
