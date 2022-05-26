package ru.tfs.spring.web.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tfs.spring.web.medical.client.model.dto.response.AddressRs;
import ru.tfs.spring.web.model.aggregate.address.Address;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    Optional<Address> findByHouseAndStreetAndCityId(String house, String street, Integer cityId);

    @Query("SELECT a.id, a.house, a.street, c.name city, r.name region " +
            "FROM t_address a " +
            "INNER JOIN t_city c ON a.city_id = c.id " +
            "INNER JOIN t_region r ON c.region_id = r.id " +
            "WHERE a.id = :id")
    Optional<AddressRs> getAddressRsByAddressId(@Param("id") Long id);
}
