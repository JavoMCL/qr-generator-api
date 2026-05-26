package jm.qr_generator_api.service;

import jm.qr_generator_api.model.QrCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("QrContentBuilder Tests")
public class QrContentBuilderTest {

    private QrContentBuilder qrContentBuilder;

    @BeforeEach
    void setUp() {
        qrContentBuilder = new QrContentBuilder();
    }

    @Test
    @DisplayName("Should build text content as-is for TEXT category")
    void testBuildTextCategory() {
        // Arrange
        String value = "Hello World";
        
        // Act
        String result = qrContentBuilder.build(QrCategory.TEXT, value);
        
        // Assert
        assertEquals("Hello World", result);
    }

    @Test
    @DisplayName("Should add https:// to URL without protocol")
    void testBuildUrlCategoryWithoutProtocol() {
        // Arrange
        String value = "example.com";
        
        // Act
        String result = qrContentBuilder.build(QrCategory.URL, value);
        
        // Assert
        assertEquals("https://example.com", result);
    }

    @Test
    @DisplayName("Should keep URL with http protocol as-is")
    void testBuildUrlCategoryWithHttpProtocol() {
        // Arrange
        String value = "http://example.com";
        
        // Act
        String result = qrContentBuilder.build(QrCategory.URL, value);
        
        // Assert
        assertEquals("http://example.com", result);
    }

    @Test
    @DisplayName("Should keep URL with https protocol as-is")
    void testBuildUrlCategoryWithHttpsProtocol() {
        // Arrange
        String value = "https://example.com";
        
        // Act
        String result = qrContentBuilder.build(QrCategory.URL, value);
        
        // Assert
        assertEquals("https://example.com", result);
    }

    @Test
    @DisplayName("Should prefix tel: for PHONE category")
    void testBuildPhoneCategory() {
        // Arrange
        String value = "+34123456789";
        
        // Act
        String result = qrContentBuilder.build(QrCategory.PHONE, value);
        
        // Assert
        assertEquals("tel:+34123456789", result);
    }

    @Test
    @DisplayName("Should prefix mailto: for EMAIL category")
    void testBuildEmailCategory() {
        // Arrange
        String value = "user@example.com";
        
        // Act
        String result = qrContentBuilder.build(QrCategory.EMAIL, value);
        
        // Assert
        assertEquals("mailto:user@example.com", result);
    }

    @Test
    @DisplayName("Should build WhatsApp link with wa.me URL")
    void testBuildWhatsAppCategory() {
        // Arrange
        String value = "34123456789";
        
        // Act
        String result = qrContentBuilder.build(QrCategory.WHATSAPP, value);
        
        // Assert
        assertEquals("https://wa.me/34123456789", result);
    }

    @Test
    @DisplayName("Should build Instagram profile link")
    void testBuildInstagramCategory() {
        // Arrange
        String value = "username";
        
        // Act
        String result = qrContentBuilder.build(QrCategory.INSTAGRAM, value);
        
        // Assert
        assertEquals("https://instagram.com/username", result);
    }

    @Test
    @DisplayName("Should build Facebook profile link")
    void testBuildFacebookCategory() {
        // Arrange
        String value = "username";
        
        // Act
        String result = qrContentBuilder.build(QrCategory.FACEBOOK, value);
        
        // Assert
        assertEquals("https://facebook.com/username", result);
    }

    @Test
    @DisplayName("Should handle empty string for TEXT category")
    void testBuildEmptyStringForTextCategory() {
        // Arrange
        String value = "";
        
        // Act
        String result = qrContentBuilder.build(QrCategory.TEXT, value);
        
        // Assert
        assertEquals("", result);
    }

    @Test
    @DisplayName("Should handle special characters in email")
    void testBuildEmailWithSpecialCharacters() {
        // Arrange
        String value = "user+test@example.co.uk";
        
        // Act
        String result = qrContentBuilder.build(QrCategory.EMAIL, value);
        
        // Assert
        assertEquals("mailto:user+test@example.co.uk", result);
    }
}

