package ru.tfs.spring.web.config;

import eu.tfs.spring.web.person.client.PersonClient;
import eu.tfs.spring.web.person.client.impl.PersonClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tfs.spring.web.medical.client.MedicalClient;
import ru.tfs.spring.web.medical.client.impl.MedicalClientImpl;
import ru.tfs.spring.web.qr.client.QrClient;
import ru.tfs.spring.web.qr.client.impl.QrClientImpl;

@Configuration
public class WebClientBuilderConfig {

    @Value("${services.person.name}")
    private String servicePersonName;
    @Value("${services.medical.name}")
    private String serviceMedicalName;
    @Value("${services.qr.name}")
    private String serviceQrName;
    private final String PROTOCOL_NAME = "lb://";

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public PersonClient personClient() {
        WebClient webClient = webClientBuilder().baseUrl(PROTOCOL_NAME + servicePersonName).build();
        return new PersonClientImpl(webClient);
    }

    @Bean
    public MedicalClient medicalClient() {
        WebClient webClient = webClientBuilder().baseUrl(PROTOCOL_NAME + serviceMedicalName).build();
        return new MedicalClientImpl(webClient);
    }

    @Bean
    public QrClient qrClient() {
        WebClient webClient = webClientBuilder().baseUrl(PROTOCOL_NAME + serviceQrName).build();
        return new QrClientImpl(webClient);
    }
}
