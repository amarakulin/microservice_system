package ru.tfs.spring.web.model.aggregate.vaccination;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table(value = "t_vaccination")
public record Vaccination (
        @Id
        Long id,
        String personName,
        String personDocumentNumber,
        String ampouleNumber,
        Date createAt,
        Date vaccinationDate,
        Integer vaccinationPointId,
        Integer vaccineId
) {}
