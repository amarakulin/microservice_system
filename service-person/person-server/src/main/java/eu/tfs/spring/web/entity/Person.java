package eu.tfs.spring.web.entity;

import eu.tfs.spring.web.person.client.model.type.DocumentType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "t_person")
@NamedEntityGraph(name = Person.GRAPH_PERSON_CONTACTS_AND_DOCUMENTS,
                attributeNodes = {
        @NamedAttributeNode(value = "contacts"),
        @NamedAttributeNode(value = "identityDocuments"),
        @NamedAttributeNode(value = "registrationAddress"),
})
public class Person {

    public static final String GRAPH_PERSON_CONTACTS_AND_DOCUMENTS =
            "graph.Person.Contacts_And_IdentityDocuments";

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "primary_doc")
    private DocumentType primaryDocument;

    @Column(name = "is_hidden")
    private Boolean isHidden = false;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "reg_address", referencedColumnName = "id")
    private Address registrationAddress;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<IdentityDocument> identityDocuments = new HashSet<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Contact> contacts = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "person_address",
    joinColumns = @JoinColumn(name = "person_id"),
    inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> addresses;

    public void addIdentityDocument(IdentityDocument identityDocument) {
        if (identityDocument != null) {
            identityDocuments.add(identityDocument);
            identityDocument.setPerson(this);
        }
    }

    public void addContact(Contact contact) {
        if (contact != null) {
            contacts.add(contact);
            contact.setPerson(this);
        }
    }

    public void deleteIdentityDocument(IdentityDocument identityDocument) {
        if (identityDocument != null) {
            identityDocuments.remove(identityDocument);
            identityDocument.setPerson(null);
        }
    }

    public void deleteContact(Contact contact) {
        if (contact != null) {
            contacts.remove(contact);
            contact.setPerson(null);
        }
    }
}
