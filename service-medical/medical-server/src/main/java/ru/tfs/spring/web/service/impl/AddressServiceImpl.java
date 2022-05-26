package ru.tfs.spring.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tfs.spring.web.medical.client.exception.InitializationDBException;
import ru.tfs.spring.web.medical.client.model.dto.request.AddressRq;
import ru.tfs.spring.web.medical.client.model.dto.response.AddressRs;
import ru.tfs.spring.web.model.aggregate.address.Address;
import ru.tfs.spring.web.model.aggregate.address.City;
import ru.tfs.spring.web.model.message.ErrorMessage;
import ru.tfs.spring.web.repository.AddressRepository;
import ru.tfs.spring.web.repository.CityRepository;
import ru.tfs.spring.web.service.AddressService;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;

    @Override
    public Address findAddressByAddressRq(AddressRq addressRq) throws InitializationDBException {
        City city = cityRepository.findByName(addressRq.city())
                .orElseThrow(() ->
                        new InitializationDBException(ErrorMessage.NOT_FOUND_ADDRESS.getMessage()));
        return addressRepository.findByHouseAndStreetAndCityId(addressRq.house(), addressRq.street(), city.id())
                .orElseThrow(() ->
                        new InitializationDBException(ErrorMessage.NOT_FOUND_ADDRESS.getMessage()));
    }

    @Override
    public AddressRs getAddressRsByAddressId(Long id) throws InitializationDBException {
        return addressRepository.getAddressRsByAddressId(id)
                .orElseThrow(() -> new InitializationDBException(ErrorMessage.NOT_FOUND_ADDRESS.getMessage()
                        .formatted(id)));

    }
}
