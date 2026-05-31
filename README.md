# Sample Microservice - Spring Boot Clean Architecture

A sample Java microservice demonstrating clean architecture principles using Spring Boot 4 with CEP (Brazilian postal code) lookup services. This project showcases best practices for building scalable, maintainable microservices with proper separation of concerns.

## 🎯 Project Overview

This microservice provides RESTful APIs to consult and search Brazilian postal addresses (CEP - Código de Endereçamento Postal) through integration with the ViaCEP API. The project serves as a reference implementation for microservice development using clean architecture patterns in Spring Boot.

**Business Capability**: Address/CEP consultation and search
**Tech Stack**: Java 21, Spring Boot 4.0.6, Spring Cloud 2025.1.1

## 📋 Key Features

- **CEP Consultation**: Retrieve complete address information from a Brazilian postal code
- **Address Search**: Search for CEP codes by state (UF), city, and street
- **Caching**: Redis-based caching for improved performance on repeated queries
- **Message Queue Integration**: RabbitMQ support for asynchronous processing
- **Data Persistence**: MongoDB integration for address data storage
- **API Documentation**: OpenAPI/Swagger UI for interactive API exploration
- **Observability**: Prometheus metrics and actuator endpoints for monitoring
- **Validation**: Bean validation with Jakarta EE constraints
- **Testing**: Testcontainers support for integration testing with real services

## 🏗️ Project Architecture

This project follows **Clean Architecture** principles with clear separation of concerns across distinct layers:

### Architecture Layers

```
┌─────────────────────────────────────────┐
│  Entrypoint Layer (Controller & Mapper) │  HTTP Interfaces & DTO Mapping
├─────────────────────────────────────────┤
│  Core Layer (Use Cases & Domain)        │  Business Logic & Domain Models
├─────────────────────────────────────────┤
│  Data Provider Layer                    │  Data Access & External APIs
└─────────────────────────────────────────┘
```

### Project Structure

```
sample-ms-sboot-clean-arch/
├── src/main/java/br/com/sample/solutionbto/
│   ├── App.java                          # Spring Boot Application Entry Point
│   │
│   ├── entrypoint/                       # Inbound Adapters (REST Controllers)
│   │   ├── controller/
│   │   │   ├── ConsultaCepController     # REST endpoints for CEP consultation
│   │   │   ├── advice/                   # Exception handling & global advice
│   │   │   └── mapper/                   # DTO to/from Domain object mapping
│   │   └── listener/                     # Message listeners (AMQP consumers)
│   │
│   ├── core/                             # Business Logic & Domain Models
│   │   ├── domain/
│   │   │   └── EnderecoCompletoDomain    # Core business entity (Address model)
│   │   │
│   │   ├── usecase/                      # Use Case Interfaces
│   │   │   └── ConsultaCepUsecase        # Address consultation contract
│   │   │
│   │   └── usecase/impl/                 # Use Case Implementations
│   │       └── ConsultaCepUsecaseImpl     # Business logic for CEP operations
│   │
│   ├── dataprovider/                     # Outbound Adapters (Data Access & APIs)
│   │   ├── mongodb/                      # MongoDB repositories & entities
│   │   ├── integration/                  # External API integrations
│   │   │   └── ViaCepFeign               # OpenFeign client for ViaCEP API
│   │   ├── impl/                         # Use Case implementations
│   │   │   └── ConsultaViaCepImpl         # Data provider for CEP lookup
│   │   └── mapper/                       # Domain to/from entity mapping
│   │
│   ├── config/                           # Application Configuration
│   │   └── WebMvcConfig                  # Spring MVC configuration
│   │
│   └── common/                           # Shared utilities & constants
│
├── src/main/resources/
│   ├── application.properties             # Spring Boot configuration
│   ├── templates/openapi/
│   │   ├── consulta-cep.yaml             # OpenAPI spec for CEP endpoints
│   │   └── viacep-integration.yaml       # OpenAPI spec for ViaCEP integration
│   ├── static/                           # Static resources
│   └── templates/                        # HTML templates if needed
│
├── src/test/java/                        # Integration & Unit Tests
│   └── br/com/sample/solutionbto/
│       └── core/
│           ├── AppTest                   # Integration tests
│           ├── TestcontainersConfiguration  # Test infrastructure setup
│           └── TestSampleMsSbootCleanArchApplication
│
├── pom.xml                               # Maven project configuration
└── LICENSE                               # Project license
```

## 📐 Architecture Components

### 1. **Entrypoint Layer** (`entrypoint/`)
The inbound adapter layer that exposes HTTP REST endpoints:
- **Controllers**: Handle incoming HTTP requests and delegate to use cases
- **Mappers**: Convert between DTOs (API contracts) and domain objects
- **Advice**: Global exception handling and error responses

### 2. **Core Layer** (`core/`)
The pure business logic layer independent from external frameworks:
- **Domain**: Core business entities (`EnderecoCompletoDomain`) - represents address data
- **Use Cases**: Interfaces defining business operations (`ConsultaCepUsecase`)
- **Use Case Implementations**: Concrete business logic orchestrating domain and data providers

### 3. **Data Provider Layer** (`dataprovider/`)
The outbound adapter layer for data access and external integrations:
- **MongoDB**: Local persistence layer for address caching
- **ViaCEP Feign Client**: HTTP integration with the ViaCEP public API
- **Repositories**: Spring Data MongoDB access patterns
- **Mappers**: Convert domain objects to/from persistence entities

### 4. **Configuration** (`config/`)
Application-wide configuration and bean setup:
- Spring MVC configuration
- Bean definitions for services
- Cross-cutting concerns setup

## 🔧 Technology Stack

### Core Frameworks
- **Java 21** - Latest LTS version with modern language features
- **Spring Boot 4.0.6** - Modern application framework with Spring 6
- **Spring Cloud 2025.1.1** - Distributed systems support

### Data & Persistence
- **MongoDB** - NoSQL database for document storage
- **Spring Data MongoDB** - ORM/ODM framework for MongoDB
- **Redis** - In-memory cache for performance optimization

### Integration & APIs
- **Spring Cloud OpenFeign** - Declarative HTTP client for external APIs
- **ViaCEP API** - Brazilian postal code lookup service

### Async & Messaging
- **RabbitMQ** - Message broker for asynchronous communication
- **Spring AMQP** - RabbitMQ integration

### Documentation & Observability
- **OpenAPI 3.0 / Swagger** - API documentation standard
- **Springdoc OpenAPI** - Automatic API documentation generation
- **Prometheus** - Metrics collection and monitoring
- **Spring Actuator** - Application health and metrics endpoints

### Code Generation & Quality
- **MapStruct** - Compile-time bean mapping code generation
- **Lombok** - Reduce boilerplate with annotations
- **OpenAPI Generator** - Generate API clients/servers from OpenAPI specs

### Testing
- **JUnit 5** - Modern testing framework
- **Testcontainers** - Docker-based test infrastructure
- **Spring Boot Test** - Spring testing utilities
- **MongoDB Testcontainer** - Containerized MongoDB for tests
- **RabbitMQ Testcontainer** - Containerized RabbitMQ for tests

## 🚀 Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.8.1 or higher
- Docker Desktop (for Testcontainers during testing)

### Build the Project

```bash
# Build the application
mvn clean install

# Build without running tests
mvn clean install -DskipTests
```

### Run the Application

```bash
# Run with Maven
mvn spring-boot:run

# Or run the JAR directly
java -jar target/sample-ms-sboot-clean-arch-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

### Application Configuration

Key properties in `application.properties`:
```properties
# Server
server.port=8080

# MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/sample-db

# Redis
spring.redis.host=localhost
spring.redis.port=6379

# RabbitMQ
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
```

## 📡 API Endpoints

The microservice exposes OpenAPI-documented REST endpoints:

### Consult CEP
```
GET /api/v1/consulta-cep?cep={CEP_CODE}
```
Returns complete address information for a given CEP.

**Example:**
```bash
curl "http://localhost:8080/api/v1/consulta-cep?cep=01310100"
```

### Search CEP by Address
```
GET /api/v1/pesquisa-cep-por-endereco?uf={STATE}&localidade={CITY}&logradouro={STREET}
```
Returns a list of CEP codes matching the address criteria.

### Interactive API Documentation
Access the embedded Swagger UI:
```
http://localhost:8080/swagger-ui.html
```

API documentation in OpenAPI format:
```
http://localhost:8080/api-docs
```

## 🧪 Testing

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=ConsultaCepControllerTest
```

### Run Integration Tests Only
```bash
mvn verify
```

The project uses **Testcontainers** for integration testing, which automatically:
- Spins up MongoDB containers
- Spins up RabbitMQ containers
- Tears down after tests complete

## 📊 Monitoring & Observability

### Actuator Endpoints
Health and metrics are available at:
```
http://localhost:8080/actuator
```

Key endpoints:
- `/actuator/health` - Application health status
- `/actuator/metrics` - Available metrics
- `/actuator/prometheus` - Prometheus metrics in correct format

### Enabling Metrics
Prometheus metrics are automatically enabled and can be scraped at:
```
http://localhost:8080/actuator/prometheus
```

## 🔄 Clean Architecture Principles Applied

1. **Independence from Frameworks**: Core business logic doesn't depend on Spring
2. **Testability**: Each layer can be tested in isolation
3. **Independence from Database**: Data layer is easily replaceable
4. **Independence from UI**: Business rules don't know about the web
5. **Independent Deployable**: Can be deployed independently

### Dependency Flow
```
Entrypoint → Core ← DataProvider
```
- **Core layer** contains no dependencies on other layers
- **Entrypoint and DataProvider** depend only on Core
- **No circular dependencies**

## 📝 Code Generation

The project uses OpenAPI Generator Maven Plugin to auto-generate API interfaces from OpenAPI specifications:

```bash
mvn clean generate-sources
```

This generates:
- REST API interfaces with proper spring annotations
- DTOs with validation constraints
- API documentation

**OpenAPI Specs Location**: `src/main/resources/templates/openapi/`

## 🔗 Default Locale & Timezone

The application is configured for Brazilian locale and timezone:
- **Locale**: Portuguese (Brazil) - pt_BR
- **Timezone**: America/Sao_Paulo
- **Encoding**: UTF-8

This is set in the `App.java` main class.

## 📦 Build Artifacts

After building, the application JAR is located at:
```
target/sample-ms-sboot-clean-arch-0.0.1-SNAPSHOT.jar
```

Both executable JAR and original JAR are generated:
- `sample-ms-sboot-clean-arch-0.0.1-SNAPSHOT.jar` - Executable
- `sample-ms-sboot-clean-arch-0.0.1-SNAPSHOT.jar.original` - Original

## 🤝 Development Workflows

### Adding a New Feature
1. **Define Use Case** → Create interface in `core/usecase/`
2. **Implement Business Logic** → Implement in `core/usecase/impl/`
3. **Define Domain Model** → Add to `core/domain/`
4. **Create Data Provider** → Implement in `dataprovider/impl/`
5. **Expose API Endpoint** → Create controller in `entrypoint/controller/`

### Adding External API Integration
1. Create Feign client in `dataprovider/integration/`
2. Implement data provider using Feign client
3. Use in core use case implementation

### Data Persistence
1. Define MongoDB entity in `dataprovider/mongodb/`
2. Create Spring Data repository
3. Use in data provider implementation

## 📄 License

See the [LICENSE](LICENSE) file for details.

## 🎓 References & Learning Resources

### Clean Architecture
- Robert C. Martin - "Clean Architecture: A Craftsman's Guide to Software Structure and Design"
- https://blog.cleancoder.com

### Spring Boot & Spring Cloud
- Official Spring Boot Documentation: https://spring.io/projects/spring-boot
- Spring Cloud Documentation: https://spring.io/projects/spring-cloud

### Technologies
- [OpenAPI / Swagger](https://swagger.io/)
- [MongoDB Documentation](https://docs.mongodb.com/)
- [RabbitMQ Documentation](https://www.rabbitmq.com/documentation.html)
- [MapStruct Documentation](https://mapstruct.org/)
- [Testcontainers](https://www.testcontainers.org/)

---

**Last Updated**: May 2026  
**Version**: 0.0.1-SNAPSHOT

