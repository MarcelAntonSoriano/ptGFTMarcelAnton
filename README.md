# ptGFTMarcelAnton

REST API built with Spring Boot and H2 in-memory database that exposes a price query endpoint for products by brand and date.

---

## Prerequisites

- Java 21+
- Maven 3.8+

---

## Running the Application

Clone the repository and run:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

### H2 Console

The H2 in-memory database console is available at:

```
http://localhost:8080/h2-console
```

Use the following credentials:

| Field | Value |
|-------|-------|
| JDBC URL | `jdbc:h2:mem:testdb` |
| Username | `sa` |
| Password | _(leave empty)_ |

The database is pre-populated with sample `PRICES` data on startup via `data.sql`.

---

## API Endpoint

### GET `/getProduct`

Returns the applicable price for a product given a brand and date.

**Request body (JSON):**

```json
{
  "date": "2020-06-14 10:00:00",
  "productId": "35455",
  "brandId": "1"
}
```

**Response body (JSON):**

```json
{
  "productId": "35455",
  "brandId": "1",
  "priceGroup": 1,
  "startDate": "2020-06-14 00:00:00",
  "endDate": "2020-12-31 23:59:59",
  "price": 35.50
}
```

**Example with curl:**

```bash
curl -X GET http://localhost:8080/getProduct \
  -H "Content-Type: application/json" \
  -d '{"date": "2020-06-14 10:00:00", "productId": "35455", "brandId": "1"}'
```

### Price Resolution Logic

When multiple price lists overlap for the same product and brand, the one with the **highest priority** value is returned.

---

## Sample Data

The database is seeded with the following prices for product `35455`, brand `1` (ZARA):

| Price List | Start Date | End Date | Priority | Price |
|------------|------------|----------|----------|-------|
| 1 | 2020-06-14 00:00:00 | 2020-12-31 23:59:59 | 0 | 35.50 € |
| 2 | 2020-06-14 15:00:00 | 2020-06-14 18:30:00 | 1 | 25.45 € |
| 3 | 2020-06-15 00:00:00 | 2020-06-15 11:00:00 | 1 | 30.50 € |
| 4 | 2020-06-15 16:00:00 | 2020-12-31 23:59:59 | 1 | 38.95 € |

---

## Running the Tests

```bash
mvn test
```

### Test Coverage

The project includes three test classes:

**`ApiControllerTest`** — Integration tests for the REST endpoint using `MockMvc`. Validates the 5 required cases for product `35455`, brand `1` (ZARA):

| Test | Request Date | Expected Price | Expected Price List |
|------|-------------|---------------|-------------------|
| 1 | 2020-06-14 10:00:00 | 35.50 € | 1 |
| 2 | 2020-06-14 16:00:00 | 25.45 € | 2 |
| 3 | 2020-06-14 21:00:00 | 35.50 € | 1 |
| 4 | 2020-06-15 10:00:00 | 30.50 € | 3 |
| 5 | 2020-06-16 21:00:00 | 38.95 € | 4 |

**`PricesServiceImplTest`** — Unit tests for the service layer, covering the happy path and the empty result case.

**`GetPriceTest`** — Unit tests for the use case layer, verifying correct mapping from service response to `ResponseDto`.

### Building without tests

```bash
mvn clean install -DskipTests
```

## Built With

- [Spring Boot 3.5.7](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [H2 Database](https://www.h2database.com/)
- [Lombok](https://projectlombok.org/)
- [JUnit 5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)
