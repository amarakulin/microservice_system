package ru.tfs.spring.web.client.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tfs.spring.web.client.PersonClient;
import ru.tfs.spring.web.medical.client.exception.PersonClientFallbackException;
import ru.tfs.spring.web.model.message.LogMessage;

@Slf4j
@Component
public class PersonClientFallback implements PersonClient {

    @Override
    public Boolean verify(String name, String passport) throws PersonClientFallbackException {
        log.error(LogMessage.FALLBACK_PERSON_CLIENT.getMessage());
        throw new PersonClientFallbackException();
    }
}
