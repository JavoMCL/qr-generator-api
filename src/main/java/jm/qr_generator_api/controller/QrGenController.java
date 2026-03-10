package jm.qr_generator_api.controller;

import jm.qr_generator_api.service.QrGenService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/qr")
@CrossOrigin(origins = "http://localhost:3000")
public class QrGenController {


    private final QrGenService qrGenService;

    public QrGenController(QrGenService qrGenService) {
        this.qrGenService = qrGenService;
    }

    @PostMapping
    public String generate(@RequestBody String content) throws Exception {
        return qrGenService.generateQrBase64(content);
    }
}
