# QR Generator API

QR Generator API is a Spring Boot REST service that creates QR codes from different content types and returns the generated image as a Base64-encoded PNG string.

## Features

- Generate QR codes from multiple categories (URL, Text, WhatsApp, Email, Phone, Instagram, Facebook)
- Return the QR image as Base64-encoded PNG
- MySQL persistence via Spring Data JPA
- Docker Compose support for local development
- CORS enabled for `http://localhost:3000`
- Automatic database schema creation and updates

## Requirements

- **For local development:**
  - Java 17+ (Maven/JVM)
  - Maven 3.6+
  - MySQL 8.0+

- **For Docker:**
  - Docker Desktop with Linux engine enabled
  - Docker Compose

## Configuration

The application reads its database settings from environment variables

The server runs on port `8080`.


```

## API Documentation

### Generate a QR Code

**Endpoint:** `POST /api/qr`

**Content-Type:** `application/json`

#### Request Body

```json
{
  "category": "URL",
  "value": "https://example.com"
}
```

#### Supported Categories

| Category | Example | Use Case |
|----------|---------|----------|
| `TEXT` | "Hello World" | Plain text content |
| `URL` | "https://example.com" | Website links |
| `WHATSAPP` | "+1234567890" | WhatsApp contact |
| `EMAIL` | "user@example.com" | Email addresses |
| `PHONE` | "+1234567890" | Phone numbers |
| `INSTAGRAM` | "username" | Instagram profiles |
| `FACEBOOK` | "username" | Facebook profiles |

#### Response

**Status:** `200 OK`

**Body:** Base64-encoded PNG image string

```
"iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAAACtWK6eAAAAAXNSR0IArs4c6..."
```

#### Usage Example

```bash
curl -X POST http://localhost:8080/api/qr \
  -H "Content-Type: application/json" \
  -d '{"category":"URL","value":"https://github.com"}'
```

## Development

### Project Structure

```
src/
├── main/
│   ├── java/jm/qr_generator_api/
│   │   ├── controller/       # REST endpoints
│   │   ├── service/          # Business logic
│   │   ├── model/            # JPA entities
│   │   ├── dto/              # Data Transfer Objects
│   │   └── QrGeneratorApiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/jm/qr_generator_api/
```

### Key Files

- **QrGenController.java** - REST endpoints for QR code generation
- **QrGenService.java** - QR code generation logic
- **QrContentBuilder.java** - Formats content based on category
- **QrGenerator.java** - JPA entity for persistence

## Database Schema

The application creates/updates the following table automatically:

```sql
CREATE TABLE qr_generator (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  category VARCHAR(50),
  value TEXT,
  qr_code LONGTEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## CORS Configuration

The API allows requests from:
- `http://localhost:3000` (default frontend port)

To modify, check `QrGenController.java`.

## Configuration Properties

**Located:** `src/main/resources/application.properties`

```properties
spring.application.name=qr-generator-api
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
```

- `ddl-auto=update` - Automatically updates database schema
- `show-sql=true` - Logs SQL queries (useful for debugging)
