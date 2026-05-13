package jm.qr_generator_api.service;

import jm.qr_generator_api.model.QrCategory;
import org.springframework.stereotype.Service;

@Service
public class QrContentBuilder {

    public String build(QrCategory category, String value) {
        return switch (category) {
            case TEXT -> value;
            case URL -> value.startsWith("http") ? value : "https://" + value;
            case PHONE -> "tel:" + value;
            case EMAIL -> "mailto:" + value;
            case WHATSAPP -> "https://wa.me/" + value;
            case INSTAGRAM -> "https://instagram.com/" + value;
            case FACEBOOK -> "https://facebook.com/" + value;
        };
    }
}
