package jm.qr_generator_api.service;

public interface IQrGenService {
    String generateQrBase64(String text) throws Exception;
}
