package ru.tfs.spring.web.model.aggregate.address;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "t_city")
public record City(
        @Id
        Integer id,
        String name,
        Integer regionId
) {}
