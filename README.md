# ead-authuser-api

Descrição
---------
API de autenticação e gerenciamento de usuários para a plataforma EAD. Projeto em Java com Spring Boot e Maven, fornecendo endpoints para registro, autenticação e operações CRUD básicas de usuário.

Principais recursos
-------------------
- Registro de usuários
- Autenticação (login)
- Gestão de usuários (buscar, atualizar, deletar)
- Validações customizadas (ex.: nome de usuário)
- Estrutura pronta para filtros/especificações e testes

Tecnologias
-----------
- Java 17+
- Spring Boot
- Maven (mvn / wrapper)
- IntelliJ IDEA (desenvolvimento)

Requisitos
---------
- JDK 17+ instalado
- Git instalado
- (Opcional) Maven instalado ou use o wrapper `mvnw` / `mvnw.cmd`

Instalação e execução (Windows / PowerShell)
--------------------------------------------
Abra PowerShell na raiz do projeto (`C:\Projetos\JAVA\EAD\authuser`) e execute:


1. Instalar/dependências e build:
```powershell
.\mvnw.cmd clean package

