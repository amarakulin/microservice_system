package eu.tfs.spring.web.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "t_region")
public class Region {

    @Id
    @GeneratedValue(generator = "seqGenReg", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seqGenReg", sequenceName = "SEQ_REG")
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Name region is mandatory")
    @Size(min = 2, max = 256, message = "Name region size too short or long")
    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    Set<Address> addresses = new HashSet<>();

    public void addAddress(Address address) {
        if (address != null) {
            addresses.add(address);
            address.setRegion(this);
        }
    }

    public void deleteAddress(Address address) {
        if (address != null) {
            addresses.remove(address);
            address.setRegion(null);
        }
    }
}
