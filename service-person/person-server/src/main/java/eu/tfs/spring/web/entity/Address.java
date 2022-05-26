package eu.tfs.spring.web.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "t_address")
public class Address {
    @Id
    @GeneratedValue(generator = "seqGenAddr", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seqGenAddr", sequenceName = "SEQ_ADDR")
    private Long id;

    @Column(name = "street")
    @NotNull(message = "Name street is mandatory")
    @Size(min = 2, max = 256, message = "Name street too short or long")
    private String street;

    @Column(name = "house")
    @NotNull(message = "Name house is mandatory")
    @Size(min = 1, max = 16, message = "Name house too short or long")
    private String house;

    @Column(name = "apartment")
    @NotNull(message = "Name apartment is mandatory")
    @Size(min = 1, max = 16, message = "Name apartment too short or long")
    private String apartment;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToMany(mappedBy = "addresses")
    private Set<Person> persons;
}
