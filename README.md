# Projeto Financeiro

Links dos repositórios:
- **Backend**: https://github.com/seu-usuario/backend-finance
- **Frontend**: https://github.com/seu-usuario/frontend-finance

---

## 📋 Visão Geral

Este é um sistema web de controle financeiro pessoal, composto por:

- **Backend** em Spring Boot + JPA, com MySQL via Docker Compose.
- **Frontend** em Angular, consumindo a API REST do backend.

Usuários podem registrar **ganhos** e **gastos**, visualizar relatórios e acompanhar históricos de transações.

---

## 📑 Sumário

1. [Backend](#backend)  
   1.1 [Pré-requisitos](#pré-requisitos-backend)  
   1.2 [Tecnologias](#tecnologias-backend)  
   1.3 [Instalação e Execução](#instalação-e-execução-backend)  
   1.4 [Endpoints Principais](#endpoints-principais)  
2. [Frontend](#frontend)  
   2.1 [Pré-requisitos](#pré-requisitos-frontend)  
   2.2 [Tecnologias](#tecnologias-frontend)  
   2.3 [Instalação e Execução](#instalação-e-execução-frontend)  
3. [📊 Diagramas UML](#diagramas-uml)  
4. [🤝 Contribuindo](#contribuindo)  
5. [⚖️ Licença](#licença)

---

## Backend

### Pré-requisitos (Backend)
- Java 17+
- Maven 3.6+
- Docker & Docker Compose
- Variáveis de ambiente:
  - `SPRING_DATASOURCE_URL` (ex: `jdbc:mysql://localhost:3306/finance_db`)
  - `SPRING_DATASOURCE_USERNAME`
  - `SPRING_DATASOURCE_PASSWORD`

### Tecnologias (Backend)
- Spring Boot
- Spring Data JPA
- MySQL
- Docker Compose

### Instalação e Execução (Backend)
```bash
# 1. Clonar o repositório
git clone https://github.com/seu-usuario/backend-finance.git
cd backend-finance

# 2. Subir o banco de dados
docker-compose up -d

# 3. Executar a aplicação
./mvnw spring-boot:run
