package eu.tfs.spring.web.service;

import eu.tfs.spring.web.entity.Contact;

import java.util.Set;

public interface ContactService {
    String getPhone(Set<Contact> contacts);
}
