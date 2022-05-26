package eu.tfs.spring.web.service.impl;

import eu.tfs.spring.web.entity.Contact;
import eu.tfs.spring.web.repository.ContactRepository;
import eu.tfs.spring.web.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final static String WITHOUT_PHONE = "Person doesn't have phone number";
    private final ContactRepository contactRepository;

    @Override
    public String getPhone(Set<Contact> contacts) {
        System.out.println(contactRepository.findAll());
        return contacts.stream()
                .filter(contact -> contact.getValue().startsWith("+79"))
                .findFirst()
                .map(Contact::getValue)
                .orElse(WITHOUT_PHONE);
    }
}
