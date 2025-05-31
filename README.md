# 📦 Agendamentos de Consultas e Serviços

Projeto desenvolvido em **Java 17** com **Spring Boot 3**, que tem como objetivo [descrever brevemente o que o projeto faz].

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security (JWT)
- PostgreSQL
- Lombok
- Swagger/OpenAPI
- Maven

## 📁 Estrutura de Diretórios

src
└── main
├── java
│ └── com.seuprojeto
│ ├── config # Segurança, JWT, Swagger, etc.
│ ├── controller # REST Controllers
│ ├── dto # Data Transfer Objects (entrada e saída)
│ ├── entity # Entidades JPA (User, Servico, etc.)
│ ├── exception # Tratamento global de exceções
│ ├── repository # Interfaces JPA
│ ├── security # Filtros, JWT, auth config
│ ├── service # Regras de negócio
│ └── util # Validadores customizados, helpers
└── resources
├── application.properties # Configurações da aplicação
└── static/templates # Se precisar usar Swagger UI, e-mails, etc.
