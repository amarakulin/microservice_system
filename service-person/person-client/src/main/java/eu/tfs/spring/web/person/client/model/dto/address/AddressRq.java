package eu.tfs.spring.web.person.client.model.dto.address;

public record AddressRq (
    String street,
    String house,
    String apartment,
    String region
) {}
