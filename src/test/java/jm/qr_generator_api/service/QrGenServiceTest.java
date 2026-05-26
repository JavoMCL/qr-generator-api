package jm.qr_generator_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("QrGenService Tests")
public class QrGenServiceTest {

    @InjectMocks
    private QrGenService qrGenService;

    @BeforeEach
    void setUp() {
        qrGenService = new QrGenService();
    }

    @Test
    @DisplayName("Should generate valid Base64 encoded QR code for simple text")
    void testGenerateQrBase64WithSimpleText() throws Exception {
        // Arrange
        String text = "Hello World";

        // Act
        String result = qrGenService.generateQrBase64(text);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isEmpty(), "Result should not be empty");
        assertTrue(isValidBase64(result), "Result should be a valid Base64 string");
    }

    @Test
    @DisplayName("Should generate valid Base64 encoded QR code for URL")
    void testGenerateQrBase64WithUrl() throws Exception {
        // Arrange
        String text = "https://www.example.com";

        // Act
        String result = qrGenService.generateQrBase64(text);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isEmpty(), "Result should not be empty");
        assertTrue(isValidBase64(result), "Result should be a valid Base64 string");
    }

    @Test
    @DisplayName("Should generate valid Base64 encoded QR code for email")
    void testGenerateQrBase64WithEmail() throws Exception {
        // Arrange
        String text = "mailto:user@example.com";

        // Act
        String result = qrGenService.generateQrBase64(text);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isEmpty(), "Result should not be empty");
        assertTrue(isValidBase64(result), "Result should be a valid Base64 string");
    }

    @Test
    @DisplayName("Should generate valid Base64 encoded QR code for phone number")
    void testGenerateQrBase64WithPhoneNumber() throws Exception {
        // Arrange
        String text = "tel:+34123456789";

        // Act
        String result = qrGenService.generateQrBase64(text);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isEmpty(), "Result should not be empty");
        assertTrue(isValidBase64(result), "Result should be a valid Base64 string");
    }

    @Test
    @DisplayName("Should generate different Base64 strings for different inputs")
    void testGenerateQrBase64WithDifferentInputs() throws Exception {
        // Arrange
        String text1 = "Hello World";
        String text2 = "Different Text";

        // Act
        String result1 = qrGenService.generateQrBase64(text1);
        String result2 = qrGenService.generateQrBase64(text2);

        // Assert
        assertNotEquals(result1, result2, "Different inputs should generate different QR codes");
    }

    @Test
    @DisplayName("Should generate identical Base64 strings for identical inputs")
    void testGenerateQrBase64WithIdenticalInputs() throws Exception {
        // Arrange
        String text = "Identical Input";

        // Act
        String result1 = qrGenService.generateQrBase64(text);
        String result2 = qrGenService.generateQrBase64(text);

        // Assert
        assertEquals(result1, result2, "Identical inputs should generate identical QR codes");
    }

    @Test
    @DisplayName("Should handle special characters in text")
    void testGenerateQrBase64WithSpecialCharacters() throws Exception {
        // Arrange
        String text = "Hello! @#$%^&*()_+-=[]{}|;:',.<>?/~`";

        // Act
        String result = qrGenService.generateQrBase64(text);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isEmpty(), "Result should not be empty");
        assertTrue(isValidBase64(result), "Result should be a valid Base64 string");
    }

    @Test
    @DisplayName("Should handle Unicode characters")
    void testGenerateQrBase64WithUnicodeCharacters() throws Exception {
        // Arrange
        String text = "¡Hola! こんにちは 你好 🚀";

        // Act
        String result = qrGenService.generateQrBase64(text);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isEmpty(), "Result should not be empty");
        assertTrue(isValidBase64(result), "Result should be a valid Base64 string");
    }

    @Test
    @DisplayName("Should handle very long text")
    void testGenerateQrBase64WithLongText() throws Exception {
        // Arrange
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append("This is a long text. ");
        }
        String text = sb.toString();

        // Act
        String result = qrGenService.generateQrBase64(text);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isEmpty(), "Result should not be empty");
        assertTrue(isValidBase64(result), "Result should be a valid Base64 string");
    }

    @Test
    @DisplayName("Should generate Base64 string with PNG header indicator")
    void testGenerateQrBase64ContainsPngSignature() throws Exception {
        // Arrange
        String text = "PNG Test";

        // Act
        String result = qrGenService.generateQrBase64(text);
        byte[] decodedBytes = Base64.getDecoder().decode(result);

        // Assert - PNG files start with specific bytes
        byte[] pngSignature = {(byte) 0x89, 0x50, 0x4E, 0x47}; // PNG magic number
        for (int i = 0; i < pngSignature.length; i++) {
            assertEquals(pngSignature[i], decodedBytes[i], 
                "Decoded data should start with PNG signature");
        }
    }

    /**
     * Helper method to validate if a string is valid Base64.
     *
     * @param value the string to validate
     * @return true if the string is valid Base64, false otherwise
     */
    private boolean isValidBase64(String value) {
        try {
            Base64.getDecoder().decode(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

