package eu.tfs.spring.web.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@Entity
@Table(name = "t_contact")
public class Contact implements PersonDepending {

    @Id
    @GeneratedValue(generator = "seqGenCont", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seqGenCont", sequenceName = "SEQ_CONT")
    private Long id;

    @Column(name = "value")
    @NotNull(message = "Contact is mandatory")
    @Size(min = 4, max = 256, message = "Contact size too short or long")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
}
