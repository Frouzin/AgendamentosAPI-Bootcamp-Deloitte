# 📦 Agendamentos de Consultas e Serviços

> Sistema simplificado de agendamento de consultas e serviços, desenvolvido com Java + Spring Boot, como desafio backend de nível pleno do bootcamp da Deloitte.

## 📌 Índice

- [🔍 Visão Geral](#-visão-geral)
- [🛠️ Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [📁 Estrutura de Diretorios](#-restrutura-de-diretorios)
- [📦 Funcionalidades Principais](#-funcionalidades-principais)
- [🔐 Autenticação e Autorização](#-autenticação-e-autorização)
- [🚀 Como Rodar o Projeto](#-como-rodar-o-projeto)
- [📌 Melhorias Futuras](#-melhorias-futuras)

---

## 🔍 Visão Geral

A **Agendamentos API** permite que usuários se cadastrem como clientes ou profissionais, definam serviços e disponibilidades, e agendem atendimentos de forma simples e segura. Conta com autenticação JWT, lógica de agenda, validações complexas e uma estrutura robusta usando as boas práticas do ecossistema Spring.

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

```plaintext
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

## 📦 Funcionalidades Principais

### 👥 Gerenciamento de Usuários
- Cadastro de clientes e profissionais.
- Login com autenticação via JWT.
- Perfis de acesso: `CLIENTE`, `PROFISSIONAL` e `ADMIN` (opcional).
- Atualização de perfil.
- Endpoint para dados do usuário autenticado.

### 🛎️ Serviços/Especialidades
- CRUD de serviços oferecidos por profissionais.
- Cada serviço possui nome, descrição, duração e preço.
- Um profissional pode oferecer múltiplos serviços.

### 🕓 Disponibilidade dos Profissionais
- Profissionais definem blocos de disponibilidade por dia e horário.
- Geração automática de horários livres (slots), excluindo agendamentos já existentes.

### 📆 Agendamento de Consultas
- Clientes visualizam profissionais, serviços e horários disponíveis.
- Agendamento com validação de conflito.
- Visualização de agendamentos (atuais e passados).
- Cancelamento com regras específicas.

### 📋 Agenda do Profissional
- Visualização da agenda por dia/semana/mês.
- Alteração de status: concluído ou cancelado (pelo profissional).

---

## 🔐 Autenticação e Autorização

A autenticação é baseada em JWT. Os endpoints são protegidos conforme o papel (`ROLE_CLIENT`, `ROLE_PROFISSIONAL`, etc), com suporte a:

- **Login e geração de token**
- **Validação automática nos filtros de segurança**
- **Controle de acesso nos endpoints com anotações do Spring Security**

---

## 🚀 Como Rodar o Projeto

```bash
# Clone o repositório
git clone https://github.com/Frouzin/AgendamentosAPI-Bootcamp-Deloitte.git
cd AgendamentosAPI-Bootcamp-Deloitte

# (Opcional) Crie um ambiente virtual
# Configure as variáveis de ambiente (ex: JWT_SECRET)

# Compile e rode o projeto
./mvnw spring-boot:run

# Acesse a API em:
http://localhost:8080


🧑‍💻 Autor
Desenvolvido por Frouzin como parte do desafio técnico do Bootcamp Deloitte.
"""
