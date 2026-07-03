# Banco de Dados — scripts SQL

Scripts para criar e popular o banco usado pela aplicação (MySQL 8).

## Ordem de execução
No MySQL, a partir desta pasta:

```sql
CREATE DATABASE steam CHARACTER SET utf8mb4;
USE steam;

SOURCE trabalho_bd_fisico.sql;     -- cria as 10 tabelas (schema)
SOURCE populacao_4000_jogos.sql;   -- popula com os 4.000 jogos
```

Depois, opcionalmente, para conferir as contagens por tabela:

```sql
SOURCE evidencias.sql;             -- COUNT(*) + amostras de cada tabela
```

## Arquivos
- `trabalho_bd_fisico.sql` — modelo físico: 10 tabelas, chaves primárias e
  estrangeiras (integridade referencial).
- `populacao_4000_jogos.sql` — INSERTs dos 4.000 jogos e dos relacionamentos.
- `evidencias.sql` — consultas de verificação (quantidade de registros por tabela).

## Observações
- O banco se chama `steam` (mesmo nome configurado em
  `src/main/resources/application.properties` da aplicação).
- Dados estáticos: captura da Steam de março/2025 (Steam Games Dataset, Kaggle).
