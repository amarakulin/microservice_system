package ru.tfs.spring.web.model.aggregate.vaccination;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "t_vaccine")
public record Vaccine (
        @Id
        Integer id,
        String name
) {}
