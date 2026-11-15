# TCMS: Test Case Management System

Backend for managing projects, test cases, test suites, test runs and results.

## Tech stack
- Java 17, Spring Boot 4, multi-module Maven
- PostgreSQL 18 (Spring Data JPA / Hibernate)
- Docker + Docker Compose
- Jenkins CI/CD, deployed to an Ubuntu VPS

## Run locally
Requirements: Docker Desktop.

```bash
cp .env.example .env        # then set DB_PASSWORD in .env
docker compose up -d --build
curl http://localhost:8080/actuator/health
```

## Build without Docker
Requirements: JDK 17, Maven, a local PostgreSQL with a `tcms` database and user.

```bash
mvn clean install
java -jar api-app/target/api-app-1.0.0.jar
```

## Deployment
Every push to `main` triggers the Jenkins pipeline (`Jenkinsfile`):
checkout → build and test (inside Docker) → deploy with Docker Compose → health check on `/actuator/health`.
