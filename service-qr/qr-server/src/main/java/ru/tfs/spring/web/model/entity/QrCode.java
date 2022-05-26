package ru.tfs.spring.web.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "t_qr_code")
public class QrCode {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "person_document_nbr")
    private String personDocumentNumber;

    @Column(name = "vaccine_name")
    private String vaccineName;

    @Column(name = "vaccination_date")
    private Date vaccinationDate;

    @Column(name = "md5_hash_code")
    private String md5HashCode;

}













