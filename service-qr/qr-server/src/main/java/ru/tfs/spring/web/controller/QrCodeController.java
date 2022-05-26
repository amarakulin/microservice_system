package ru.tfs.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tfs.spring.web.qr.client.exception.PassportNotExistException;
import ru.tfs.spring.web.qr.client.model.dto.response.QrCodeRs;
import ru.tfs.spring.web.qr.client.model.dto.response.VerifyRs;
import ru.tfs.spring.web.service.QrCodeService;

@RestController
@RequestMapping("/qr")
@RequiredArgsConstructor
public class QrCodeController {

    private final QrCodeService qrCodeService;

    @GetMapping("/{passport}")
    public QrCodeRs getQrCodeByPassport(@PathVariable String passport) throws PassportNotExistException {
        return qrCodeService.getQrCodeRsByPassport(passport);
    }

    @GetMapping("/check")
    public VerifyRs verifyQrCode(@RequestParam String code) {
        return qrCodeService.verifyQrCodeByMd5Hash(code);
    }
}
