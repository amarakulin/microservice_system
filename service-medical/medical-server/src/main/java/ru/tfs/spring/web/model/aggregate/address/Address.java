package ru.tfs.spring.web.model.aggregate.address;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "t_address")
public record Address (
        @Id
        Long id,
        String house,
        String street,
        Integer cityId
) {}
