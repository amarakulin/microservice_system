package ru.tfs.spring.web.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import ru.tfs.spring.web.medical.client.model.dto.response.InfoForQrCode;
import ru.tfs.spring.web.model.message.ErrorMessage;
import ru.tfs.spring.web.model.message.LogMessage;
import ru.tfs.spring.web.service.VaccinationService;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class QrCodeScheduler {

    @Value("${scheduler.send_qr_code.milliseconds}")
    private Long rateQrSendTime;
    @Value("${kafka.topic.qr}")
    private String topic;
    private final VaccinationService vaccinationService;
    private final KafkaTemplate<UUID, InfoForQrCode> kafkaTemplate;

    @Scheduled(fixedRateString = "${scheduler.send_qr_code.milliseconds}",
                initialDelayString = "${scheduler.initial_delay.milliseconds}")
    public void sendDataForQrCode() throws ExecutionException, InterruptedException {
        Date lastTimeSend = Date.from(Instant.now().minusMillis(rateQrSendTime));
        List<InfoForQrCode> listQrCodeRs = vaccinationService.getListQrCodeRsAfter(lastTimeSend);
        if (!listQrCodeRs.isEmpty()) {
            listQrCodeRs.forEach(this::sendInfoForQrCode);
        }
    }

    private void sendInfoForQrCode(InfoForQrCode infoQr) {
        ListenableFuture<SendResult<UUID, InfoForQrCode>> sendResult = kafkaTemplate.send(topic, infoQr);

        sendResult.addCallback(new ListenableFutureCallback<SendResult<UUID, InfoForQrCode>>() {

            @Override
            public void onSuccess(SendResult<UUID, InfoForQrCode> result) {
                log.info(LogMessage.SEND_QR_INFO_SUCCESS.getMessage()
                        .formatted(infoQr.personName(), infoQr.vaccinationDate()));
            }
            @Override
            public void onFailure(Throwable ex) {
                log.error(ErrorMessage.SEND_QR_INFO_FAILURE.getMessage()
                        .formatted(infoQr.personName(), infoQr.personName()));
                log.error("Exception: " + ex.getMessage());
            }
        });
    }
}
