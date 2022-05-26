package ru.tfs.spring.web.service;

import ru.tfs.spring.web.medical.client.exception.InitializationDBException;
import ru.tfs.spring.web.medical.client.model.dto.request.AddressRq;
import ru.tfs.spring.web.medical.client.model.dto.response.AddressRs;
import ru.tfs.spring.web.model.aggregate.address.Address;

public interface AddressService {
    Address findAddressByAddressRq(AddressRq addressRq) throws InitializationDBException;
    AddressRs getAddressRsByAddressId(Long id) throws InitializationDBException;
}
