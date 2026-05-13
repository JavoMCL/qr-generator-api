package jm.qr_generator_api.controller;

import jm.qr_generator_api.dto.QrRequest;
import jm.qr_generator_api.service.QrContentBuilder;
import jm.qr_generator_api.service.QrGenService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/qr")
@CrossOrigin(origins = "http://localhost:3000")
public class QrGenController {


    private final QrGenService qrGenService;
    private final QrContentBuilder qrContentBuilder;

    public QrGenController(QrGenService qrGenService, QrContentBuilder qrContentBuilder) {
        this.qrGenService = qrGenService;
        this.qrContentBuilder = qrContentBuilder;
    }

    @PostMapping
    public String generate(@RequestBody QrRequest request) throws Exception {
        String content = qrContentBuilder.build(
                request.getCategory(),
                request.getValue()
        );

        return qrGenService.generateQrBase64(content);
    }
}
