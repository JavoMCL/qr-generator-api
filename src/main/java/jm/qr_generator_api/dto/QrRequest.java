package jm.qr_generator_api.dto;

import jm.qr_generator_api.model.QrCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QrRequest {

    private QrCategory category;
    private String value;

    public QrCategory getCategory() {
        return category;
    }

    public void setCategory(QrCategory category) {
        this.category = category;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
