package ru.tfs.spring.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tfs.spring.web.client.fallback.PersonClientFallback;
import ru.tfs.spring.web.medical.client.exception.PersonClientFallbackException;

@FeignClient(value = "service-person", fallback = PersonClientFallback.class)
public interface PersonClient {

    @RequestMapping(value = "/person/verify", method = RequestMethod.GET)
    Boolean verify(@RequestParam("name") String name, @RequestParam("passport") String passport) throws PersonClientFallbackException;
}