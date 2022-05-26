package ru.tfs.spring.web.model.aggregate.vaccination;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "t_vaccination_point")
public record VaccinationPoint (
        @Id
        Integer id,
        String codePoint,
        String name,
        Long addressId
) {}
