# Trabalho Final de Banco de Dados I — Jogos da Steam (Grupo 10)

Aplicação web para explorar e visualizar um banco de dados relacional do catálogo
de jogos da Steam. Projeto da disciplina **Banco de Dados I (ICP489) — UFRJ**.

## Integrantes (Grupo 10)
- Matheus Araujo Ramalho Rodrigues
- Nicolle Shayre Ferreira e Silva
- Patrick Jose Madruga Teixeira de Carvalho
- Yuri Altomare de Carvalho

## Sobre o projeto
- **Dados:** Steam Games Dataset 2025 (Kaggle), captura de março/2025. Do acervo
  original (~89 mil jogos) foram usados os **4.000 primeiros**, com 20 colunas por jogo.
  Os dados são **estáticos** (uma foto de 2025).
- **Banco:** modelo relacional em **MySQL** com 10 tabelas (entidades JOGO, IDIOMA,
  CATEGORIA, GÊNERO e TAG; tabelas associativas dos relacionamentos n:n; e a tabela
  do atributo multivalorado `plataforma_suportada`).
- **Aplicação:** consulta e visualiza os dados (foco em leitura, sem inserir/alterar).
  Inspirada no SteamDB.

## Funcionalidades
- 10 consultas SQL prontas (junções, junção externa, agregações e subconsultas).
- Página de estatísticas com gráficos (rosca e barras) via Chart.js.
- Busca dinâmica por nome de jogo (funcionalidade extra).

## Tecnologias
- Java + Spring Boot (Spring MVC, Spring Data JDBC)
- Thymeleaf (templates) e Chart.js (gráficos)
- MySQL 8
- Maven

## Como executar

### Pré-requisitos
- Java 17+ e Maven (ou use o wrapper `./mvnw` incluído)
- MySQL 8 rodando localmente

### 1. Criar e popular o banco
No MySQL, crie o banco e as tabelas, depois popule:
```sql
CREATE DATABASE steam CHARACTER SET utf8mb4;
USE steam;
SOURCE banco/trabalho_bd_fisico.sql;      -- cria as 10 tabelas
SOURCE banco/populacao_4000_jogos.sql;    -- popula com os 4.000 jogos
```
> Os scripts SQL estão na pasta `banco/` deste repositório.

### 2. Configurar o acesso ao banco
Em `src/main/resources/application.properties`, ajuste usuário e senha do seu MySQL:
spring.datasource.url=jdbc:mysql://localhost:3306/steam?serverTimezone=UTC
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

### 3. Rodar a aplicação
```bash
./mvnw spring-boot:run
```
Acesse em: http://localhost:8080

## Estrutura
- `src/main/java/steam_web/` — código (controller, service, repository, DTOs)
- `src/main/resources/templates/` — páginas Thymeleaf
- `banco/` — scripts SQL (criação e população do banco)
