# money-transfer-service

RESTful API (including data model and the backing implementation) for money transfers between two accounts. 
 
# Tech Stack
- SparkJava
- Guice
- Jooq 
- Flyway
- Hibernate - for db to pojo mapping
- Hibernate Validator
- REST Assured
- H2 database

# Overview
To transfer money between two accounts we need to create account first via RESTful API
but for simplicity I have added two account in database via flyway script. Currently service handles 
only 1 currency, so have not handled currency conversion related logic.

## API Endpoint

API URL: `POST http://localhost:8080/api/transfer`

Request Body:
```json
{
  "fromAccountId": "d1834642-2add-4b11-a0a6-decd09f4d237",
  "toAccountId": "c1834642-2add-4b11-a0a6-decd09f4d237",
  "amount": 100,
  "details": "Lunch payment"
}
```
Content-Type: application/json

Response:

Status: 201 Created 

```json
{
  "id": "52334b6a-22fb-452a-9acf-8f02df01c13e",
  "fromAccountId": "d1834642-2add-4b11-a0a6-decd09f4d237",
  "toAccountId": "c1834642-2add-4b11-a0a6-decd09f4d237",
  "amount": 1,
  "details": "Lunch payment",
  "status": 3
}

```
- status [0 - Pending, 1 - sender account charged, 2 - FAILED, 3 - money transferred]

Note: Service runs on port 8080, can be update via /src/main/resources/application.properties

## Running Tests
./mvnw clean test

## Create Executable Jar
./mvnw clean package

## Running Jar File

java -jar ./target/money-transfer-service-1.0.0-SNAPSHOT.jar