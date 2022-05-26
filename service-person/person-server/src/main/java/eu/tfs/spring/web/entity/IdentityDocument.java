package eu.tfs.spring.web.entity;

import eu.tfs.spring.web.person.client.model.type.DocumentType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@Entity
@Table(name = "t_document")
public class IdentityDocument implements PersonDepending {
    @Id
    @GeneratedValue(generator = "seqGenDoc", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seqGenDoc", sequenceName = "SEQ_DOC")
    private Long id;

    @Column(name = "type")
    @NotNull(message = "Document type is mandatory")
    private DocumentType type;

    @Column(name = "number")
    @NotNull(message = "Document number is mandatory")
    @Size(min = 4, max = 256, message = "Document number size too short or long")
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
}
