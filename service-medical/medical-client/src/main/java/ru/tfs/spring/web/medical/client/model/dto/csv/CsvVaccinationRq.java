package ru.tfs.spring.web.medical.client.model.dto.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class CsvVaccinationRq {
        @CsvBindByName(column = "name")
        private String personName;
        @CsvBindByName(column = "document_nbr")
        private String personDocumentNumber;
        @CsvBindByName(column = "ampoule_nbr")
        private String ampouleNumber;
        @CsvBindByName(column = "vaccination_date")
        private String vaccinationDate;
        @CsvBindByName(column = "name_vaccine")
        private String nameVaccine;
        @CsvBindByName(column = "vaccination_point_nbr")
        private String codeVaccinationPoint;
        @CsvBindByName(column = "vaccination_point_name")
        private String nameVaccinationPoint;
        @CsvBindByName
        private String house;
        @CsvBindByName
        private String street;
        @CsvBindByName
        private String city;
        @CsvBindByName
        private String region;
}