CREATE DATABASE IF NOT EXISTS steam_games CHARACTER SET utf8mb4;
USE steam_games;

CREATE TABLE Jogo (
    app_id INT PRIMARY KEY,
    nome VARCHAR(255),
    data_lancamento DATE,
    idade_requerida INT,
    preco DECIMAL(10,2),
    quantidade_dlc INT,
    descricao_detalhada TEXT,
    tempo_jogo_mediano INT,
    texto_reviews TEXT,
    recomendacoes_ INT,
    pontuacao_usuario_ INT,
    total_reviews INT,
    prop_min INT,
    prop_max INT
);

CREATE TABLE Idioma (
    id_idioma INT PRIMARY KEY,
    nome_idioma VARCHAR(100)
);

CREATE TABLE Genero (
    id_genero INT PRIMARY KEY,
    nome_genero VARCHAR(100)
);

CREATE TABLE Tag (
    id_tag INT PRIMARY KEY,
    nome_tag VARCHAR(100)
);

CREATE TABLE Categoria (
    id_categoria INT PRIMARY KEY,
    nome_categoria VARCHAR(150)
);

CREATE TABLE plataforma_suportada (
    fk_Jogo_app_id INT,
    plataforma_suportada VARCHAR(10),
    PRIMARY KEY (fk_Jogo_app_id, plataforma_suportada),
    FOREIGN KEY (fk_Jogo_app_id) REFERENCES Jogo (app_id) ON DELETE CASCADE
);

CREATE TABLE suporta (
    fk_Jogo_app_id INT,
    fk_Idioma_id_idioma INT,
    PRIMARY KEY (fk_Jogo_app_id, fk_Idioma_id_idioma),
    FOREIGN KEY (fk_Jogo_app_id) REFERENCES Jogo (app_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_Idioma_id_idioma) REFERENCES Idioma (id_idioma) ON DELETE CASCADE
);

CREATE TABLE possui (
    fk_Jogo_app_id INT,
    fk_Categoria_id_categoria INT,
    PRIMARY KEY (fk_Jogo_app_id, fk_Categoria_id_categoria),
    FOREIGN KEY (fk_Jogo_app_id) REFERENCES Jogo (app_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_Categoria_id_categoria) REFERENCES Categoria (id_categoria) ON DELETE CASCADE
);

CREATE TABLE classificado_como (
    fk_Jogo_app_id INT,
    fk_Genero_id_genero INT,
    PRIMARY KEY (fk_Jogo_app_id, fk_Genero_id_genero),
    FOREIGN KEY (fk_Jogo_app_id) REFERENCES Jogo (app_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_Genero_id_genero) REFERENCES Genero (id_genero) ON DELETE CASCADE
);

CREATE TABLE marcado_com (
    fk_Jogo_app_id INT,
    fk_Tag_id_tag INT,
    votos INT,
    PRIMARY KEY (fk_Jogo_app_id, fk_Tag_id_tag),
    FOREIGN KEY (fk_Jogo_app_id) REFERENCES Jogo (app_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_Tag_id_tag) REFERENCES Tag (id_tag) ON DELETE CASCADE
);
