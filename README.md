# Projeto Financeiro - Frontend e Backend

Bem-vindo ao projeto financeiro! Este repositório contém links e informações sobre os dois componentes principais do sistema:

- **[Backend](#backend)**: Construído com Spring Boot e JPA, utilizando MySQL como banco de dados, e gerenciado com Docker Compose.
- **[Frontend](#frontend)**: Desenvolvido com Angular para fornecer uma interface de usuário rica e responsiva.

---

## Links dos Repositórios

- [Repositório do Backend](https://github.com/seu-usuario/backend-repo)
- [Repositório do Frontend](https://github.com/seu-usuario/frontend-repo)

---

## Visão Geral do Projeto

Este é um sistema para controle financeiro pessoal, permitindo gerenciar contas, registrar transações (ganhos e gastos) e visualizar um histórico consolidado. O sistema é dividido em dois repositórios: o backend fornece a API e a lógica de negócios, enquanto o frontend oferece a interface para os usuários finais.

---

## Diagramas UML

Abaixo estão os diagramas UML que ilustram aspectos importantes do projeto.

### Estrutura de Domínio

O diagrama abaixo representa a estrutura principal do sistema, mostrando as entidades usadas no backend para gerenciar contas e transações:

![Diagrama UML](path/to/uml-diagram.png)

### Outros Diagramas

(Adicione outros diagramas UML nesta seção, conforme necessário.)

---

## Backend

### Tecnologias Utilizadas

- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **Docker Compose**
- **Hibernate**

### Requisitos de Instalação

1. **Pré-requisitos**:
   - [Docker](https://www.docker.com/)

2. **Configuração**:
   - Clone o repositório do backend:  
     ```bash
     git clone https://github.com/seu-usuario/backend-repo.git
     cd backend-repo
     ```
   - Atualize o arquivo `application.yml` com as configurações do banco de dados, se necessário.

3. **Rodando com Docker Compose**:
   - Execute o comando abaixo para inicializar o banco de dados e a aplicação:
     ```bash
     docker-compose up --build
     ```

4. **API Endpoints**:
   - Acesse a documentação da API (Swagger) em: `http://localhost:8080/swagger-ui.html`

---

## Frontend

### Tecnologias Utilizadas

- **Angular**
- **TypeScript**
- **Bootstrap** (ou outro framework CSS, caso aplicável)

### Requisitos de Instalação

1. **Pré-requisitos**:
   - [Node.js](https://nodejs.org/) (versão 16 ou superior)
   - [Angular CLI](https://angular.io/cli)

2. **Configuração**:
   - Clone o repositório do frontend:  
     ```bash
     git clone https://github.com/seu-usuario/frontend-repo.git
     cd frontend-repo
     ```
   - Instale as dependências:
     ```bash
     npm install
     ```

3. **Rodando a Aplicação**:
   - Execute o comando abaixo para iniciar o servidor local:
     ```bash
     ng serve
     ```
   - Acesse o sistema em: `http://localhost:4200`

4. **Configuração da API**:
   - Atualize o arquivo `environment.ts` com a URL do backend, se necessário:
     ```typescript
     export const environment = {
       production: false,
       apiUrl: 'http://localhost:8080/api'
     };
     ```

---

## Contribuição

1. Faça um fork do repositório.
2. Crie um branch para a sua feature/bugfix:
   ```bash
   git checkout -b minha-feature
   ```
3. Faça commit das suas alterações:
   ```bash
   git commit -m 'Adicionando minha feature'
   ```
4. Envie suas alterações:
   ```bash
   git push origin minha-feature
   ```
5. Abra um Pull Request.

---

## Melhorias Futuras

- Em andamento.
---

## Contato

Em caso de dúvidas ou sugestões, entre em contato via [email@example.com](mailto:email@example.com).
