package ru.tfs.spring.web.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.UUIDDeserializer;
import org.apache.kafka.common.serialization.UUIDSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.tfs.spring.web.qr.client.model.dto.request.QrCodeRq;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EnableKafka
@Configuration
public class QrCodeConsumerConfig {

    @Value("${kafka.address}")
    private String address;
    @Value("${kafka.group_id.qr}")
    private String groupIdQr;

    @Bean
    public Map<String, Object> config() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupIdQr);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return config;
    }

    @Bean
    public JsonDeserializer<QrCodeRq> deserializer() {
        JsonDeserializer<QrCodeRq> deserializer = new JsonDeserializer<>(QrCodeRq.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return deserializer;
    }

    @Bean
    public ConsumerFactory<UUID, QrCodeRq> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(config(),
                new UUIDDeserializer(), deserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<UUID, QrCodeRq> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<UUID, QrCodeRq> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
