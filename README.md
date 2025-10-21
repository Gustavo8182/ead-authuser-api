# ead-authuser-api

API de Autenticação e Gerenciamento de Usuários para Plataforma EAD  
Projeto em Java com Spring Boot (via Maven), criado para oferecer endpoints de registro, login e operações CRUD de usuário.

---

## 🧾 Índice

- [Visão Geral](#visão-geral)  
- [Funcionalidades](#funcionalidades)  
- [Tecnologias](#tecnologias)  
- [Pré-requisitos](#pré-requisitos)  
- [Instalação & Execução](#instalação--execução)  
- [Configuração](#configuração)  
- [Endpoints](#endpoints)  
- [Testes](#testes)  
- [Melhorias Futuras](#melhorias-futuras)  
- [Licença](#licença)  
- [Contato](#contato)  

---

## Visão Geral

Este projeto entrega uma API backend para autenticação e gerenciamento de usuários num contexto de ensino a distância (EAD). A ideia é disponibilizar de forma simples:

- Registro de novos usuários  
- Login/autenticação  
- Consultar, atualizar ou remover usuários  
- Validações customizadas (por exemplo: nome de usuário único)  
- Estrutura preparada para filtros/especificações e testes

---

## Funcionalidades

- ✅ Registro de usuário (endpoint para criação)  
- ✅ Login / obtenção de token (ou mecanismo escolhido)  
- ✅ CRUD de usuários (listar, obter por ID, atualizar, deletar)  
- ✅ Validações de dados (ex: nome de usuário, email, formato)  
- ✅ Arquitetura preparada para futuras extensões (filtros, especificações, testes)  

---

## Tecnologias

- Java 17+  
- Spring Boot  
- Maven (wrapper incluído)  
- (IDE recomendada: IntelliJ IDEA)  
- (Banco de dados: conforme configuração — ex. H2, PostgreSQL, MySQL)  

---

## Pré-requisitos

Antes de rodar o projeto, certifique-se de ter:

- JDK 17 ou superior instalado  
- Git instalado  
- (Opcional) Maven instalado, ou utilizar o wrapper (`mvnw` / `mvnw.cmd`)  
- Um banco de dados configurado conforme `application.properties` ou `application.yml`  

---
## Configuração

  -Edite o arquivo src/main/resources/application.properties ou application.yml para definir:
  
  -Porta da aplicação (ex: server.port=8080)
  
  -Conexão com banco de dados (URL, usuário, senha)
  
  -Propriedades de segurança (ex: JWT, chaves, tempo de expiração)
  
  -Outras variáveis de ambiente que sejam necessárias
---
## Endpoints (exemplo)

Nota: Ajustar conforme URLs reais e detalhes do seu projeto.

POST /api/users/register — registrar novo usuário

POST /api/users/login — autenticar usuário

GET /api/users — listar todos os usuários

GET /api/users/{id} — obter usuário por ID

PUT /api/users/{id} — atualizar dados do usuário

DELETE /api/users/{id} — remover usuário
---


## Instalação & Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/Gustavo8182/ead-authuser-api.git
   cd ead-authuser-api
   
2. Compile e gere o artefato:
   ```bash
      # Windows
        .\mvnw.cmd clean package
      # macOS / Linux
        ./mvnw clean package
3. Execute a aplicação:
   ```bash
     # Windows
      .\mvnw.cmd spring-boot:run
     # macOS / Linux
      ./mvnw spring-boot:run
4.Acesse a API em http://localhost:8080 (ou conforme configurado).
      





