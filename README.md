
<h1>Estrutura de Diretórios</h1>
src
└── main
    ├── java
    │   └── com.seuprojeto
    │       ├── config               # Segurança, JWT, Swagger, etc.
    │       ├── controller           # REST Controllers
    │       ├── dto                  # Data Transfer Objects (entrada e saída)
    │       ├── entity               # Entidades JPA (User, Servico, etc.)
    │       ├── exception            # Tratamento global de exceções
    │       ├── repository           # Interfaces JPA
    │       ├── security             # Filtros, JWT, auth config
    │       ├── service              # Regras de negócio
    │       └── util                 # Validadores customizados, helpers
    └── resources
        ├── application.properties   # Configurações da aplicação
        └── static/templates         # Se precisar usar Swagger UI, e-mails, etc.
