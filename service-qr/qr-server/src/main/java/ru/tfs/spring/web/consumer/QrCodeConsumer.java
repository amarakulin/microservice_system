package ru.tfs.spring.web.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.tfs.spring.web.qr.client.model.dto.request.QrCodeRq;
import ru.tfs.spring.web.service.QrCodeService;

@Component
@RequiredArgsConstructor
public class QrCodeConsumer {

    private final QrCodeService qrCodeService;

    @KafkaListener(topics = "${kafka.topic.qr}", groupId = "${kafka.group_id.qr}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenQrCodes(QrCodeRq qrCodeRq) {
        qrCodeService.save(qrCodeRq);
    }
}
