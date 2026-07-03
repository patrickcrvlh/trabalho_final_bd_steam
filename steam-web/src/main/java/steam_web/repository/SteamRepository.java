package steam_web.repository;

import steam_web.dto.EngajamentoDTO;
import steam_web.dto.JogoDTO;
import steam_web.dto.consultas.PontuacaoDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import steam_web.dto.consultas.*;
import steam_web.dto.graficos.*;

import java.util.List;

@Repository
public class SteamRepository {

    private final JdbcTemplate jdbcTemplate;

    public SteamRepository(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;

    }

    public List<EngajamentoDTO> rankingEngajamento(){

        String sql = """
                SELECT
                    j.nome,
                    SUM(m.votos) AS engajamento
                FROM Jogo j
                JOIN marcado_com m
                    ON j.app_id = m.fk_Jogo_app_id
                GROUP BY j.app_id,j.nome
                ORDER BY engajamento DESC
                LIMIT 10
                """;

        return jdbcTemplate.query(sql,
                (rs,row)->new EngajamentoDTO(
                        rs.getString("nome"),
                        rs.getInt("engajamento")
                ));

    }

    public int totalJogos(){

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM Jogo",
                Integer.class
        );

    }

    public int totalIdiomas(){

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM Idioma",
                Integer.class
        );

    }

    public int totalGeneros(){

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM Genero",
                Integer.class
        );

    }

    public int totalTags(){

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM Tag",
                Integer.class
        );

    }

    public List<JogoDTO> buscarPorNome(
            String nome,
            int pagina,
            int tamanho){

        String sql = """
        SELECT
            app_id,
            nome,
            preco,
            total_reviews
        FROM Jogo
        WHERE nome LIKE ?
        ORDER BY nome
        LIMIT ?
        OFFSET ?
        """;

        int offset = pagina * tamanho;

        return jdbcTemplate.query(
                sql,
                new Object[]{
                        "%" + nome + "%",
                        tamanho,
                        offset
                },
                (rs, row) -> new JogoDTO(

                        rs.getInt("app_id"),

                        rs.getString("nome"),

                        rs.getDouble("preco"),

                        rs.getInt("total_reviews")
                )
        );

    }

    public int contarJogos(String nome) {

        String sql = """
        SELECT COUNT(*)
        FROM Jogo
        WHERE nome LIKE ?
        """;

        return jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                "%" + nome + "%"
        );

    }

    public List<Consulta1DTO> consulta1(){

        String sql = """
        SELECT
            j.nome,
            g.nome_genero
        FROM Jogo j
        JOIN classificado_como cc
            ON j.app_id = cc.fk_Jogo_app_id
        JOIN Genero g
            ON cc.fk_Genero_id_genero = g.id_genero
        LIMIT 10
        """;

        return jdbcTemplate.query(

                sql,

                (rs,row)->new Consulta1DTO(

                        rs.getString("nome"),

                        rs.getString("nome_genero")

                )

        );

    }

    public List<Consulta2DTO> consulta2(){

        String sql = """
        SELECT
            j.nome,
            c.nome_categoria,
            i.nome_idioma
        FROM Jogo j
        JOIN possui p
            ON j.app_id = p.fk_Jogo_app_id
        JOIN Categoria c
            ON p.fk_Categoria_id_categoria = c.id_categoria
        JOIN suporta s
            ON j.app_id = s.fk_Jogo_app_id
        JOIN Idioma i
            ON s.fk_Idioma_id_idioma = i.id_idioma
        LIMIT 10
        """;

        return jdbcTemplate.query(sql,

                (rs,row)->new Consulta2DTO(

                        rs.getString("nome"),

                        rs.getString("nome_categoria"),

                        rs.getString("nome_idioma")

                )

        );

    }

    public List<Consulta3DTO> consulta3(){

        String sql = """
        SELECT
            g.nome_genero,
            COUNT(*) AS quantidade_jogos
        FROM Genero g
        JOIN classificado_como cc
            ON g.id_genero = cc.fk_Genero_id_genero
        GROUP BY g.nome_genero
        ORDER BY quantidade_jogos DESC
        LIMIT 10
        """;

        return jdbcTemplate.query(

                sql,

                (rs,row)->new Consulta3DTO(

                        rs.getString("nome_genero"),

                        rs.getInt("quantidade_jogos")

                )

        );

    }

    public List<Consulta4DTO> consulta4(){

        String sql = """
        SELECT
            j.nome,
            g.nome_genero,
            j.preco,
            ROUND(medias.preco_medio_genero,2) AS preco_medio_genero
        FROM Jogo j
        JOIN classificado_como c
            ON j.app_id = c.fk_Jogo_app_id
        JOIN Genero g
            ON c.fk_Genero_id_genero = g.id_genero
        JOIN (
            SELECT
                c_in.fk_Genero_id_genero,
                AVG(j_in.preco) AS preco_medio_genero
            FROM Jogo j_in
            JOIN classificado_como c_in
                ON j_in.app_id = c_in.fk_Jogo_app_id
            GROUP BY c_in.fk_Genero_id_genero
        ) medias
        ON medias.fk_Genero_id_genero = g.id_genero
        WHERE j.preco > medias.preco_medio_genero
        LIMIT 10
        """;

        return jdbcTemplate.query(

                sql,

                (rs,row)->new Consulta4DTO(

                        rs.getString("nome"),

                        rs.getString("nome_genero"),

                        rs.getDouble("preco"),

                        rs.getDouble("preco_medio_genero")

                )

        );

    }

    public List<Consulta5DTO> consulta5(){

        String sql = """
        SELECT
            nome,
            recomendacoes
        FROM Jogo
        WHERE recomendacoes >
        (
            SELECT AVG(recomendacoes)
            FROM Jogo
        )
        LIMIT 10
        """;

        return jdbcTemplate.query(

                sql,

                (rs,row)->new Consulta5DTO(

                        rs.getString("nome"),

                        rs.getInt("recomendacoes")

                )

        );

    }

    public List<Consulta6DTO> consulta6(){

        String sql = """
        SELECT
            t.nome_tag,
            j.nome
        FROM Tag t
        LEFT JOIN marcado_com mc
            ON t.id_tag = mc.fk_Tag_id_tag
        LEFT JOIN Jogo j
            ON mc.fk_Jogo_app_id = j.app_id
        LIMIT 10
        """;

        return jdbcTemplate.query(

                sql,

                (rs,row)->new Consulta6DTO(

                        rs.getString("nome_tag"),

                        rs.getString("nome")

                )

        );

    }

    public List<Consulta8DTO> consulta8(){

        String sql = """
        SELECT
            j1.nome,
            t.nome_tag
        FROM Jogo j1
        JOIN marcado_com mc1
            ON j1.app_id = mc1.fk_Jogo_app_id
        JOIN Tag t
            ON mc1.fk_Tag_id_tag = t.id_tag
        WHERE t.id_tag NOT IN (

            SELECT mc2.fk_Tag_id_tag

            FROM marcado_com mc2

            WHERE mc2.fk_Jogo_app_id <> j1.app_id

        )

        ORDER BY j1.nome

        LIMIT 10
        """;

        return jdbcTemplate.query(

                sql,

                (rs,row)->new Consulta8DTO(

                        rs.getString("nome"),

                        rs.getString("nome_tag")

                )

        );

    }

    public List<Consulta9DTO> consulta9(){

        String sql = """
        SELECT
            nome,
            preco
        FROM Jogo
        WHERE preco = (
            SELECT MAX(preco)
            FROM Jogo
        )
        LIMIT 10
        """;

        return jdbcTemplate.query(

                sql,

                (rs,row)->new Consulta9DTO(

                        rs.getString("nome"),

                        rs.getDouble("preco")

                )

        );

    }

    public List<PontuacaoDTO> consulta10() {

        String sql = """
        (
            SELECT
                nome,
                total_reviews
            FROM Jogo
            ORDER BY total_reviews DESC
            LIMIT 3
        )

        UNION

        (
            SELECT
                nome,
                total_reviews
            FROM Jogo
            ORDER BY total_reviews ASC
            LIMIT 3
        )

        ORDER BY total_reviews DESC;
        """;

        return jdbcTemplate.query(
                sql,
                (rs, row) -> new PontuacaoDTO(
                        rs.getString("nome"),
                        rs.getInt("total_reviews")
                )
        );
    }

    public List<GeneroGraficoDTO> buscarGeneros() {

        String sql = """
        SELECT
            g.nome_genero,
            COUNT(*) AS quantidade
        FROM Genero g
        JOIN classificado_como c
            ON g.id_genero = c.fk_Genero_id_genero
        GROUP BY g.nome_genero
        ORDER BY quantidade DESC
        """;

        return jdbcTemplate.query(

                sql,

                (rs, row) -> new GeneroGraficoDTO(

                        rs.getString("nome_genero"),

                        rs.getInt("quantidade")

                )

        );

    }

    public List<IdiomaGraficoDTO> buscarIdiomas() {

        String sql = """
        SELECT
            i.nome_idioma,
            COUNT(*) AS quantidade
        FROM Idioma i
        JOIN suporta s
            ON i.id_idioma = s.fk_Idioma_id_idioma
        GROUP BY i.id_idioma, i.nome_idioma
        ORDER BY quantidade DESC
        LIMIT 10
        """;

        return jdbcTemplate.query(

                sql,

                (rs, row) -> new IdiomaGraficoDTO(

                        rs.getString("nome_idioma"),

                        rs.getInt("quantidade")

                )

        );

    }

    public List<TagGraficoDTO> buscarTags() {

        String sql = """
        SELECT
            t.nome_tag,
            COUNT(*) AS quantidade
        FROM Tag t
        JOIN marcado_com m
            ON t.id_tag = m.fk_Tag_id_tag
        GROUP BY t.id_tag, t.nome_tag
        ORDER BY quantidade DESC
        LIMIT 10
        """;

        return jdbcTemplate.query(

                sql,

                (rs, row) -> new TagGraficoDTO(

                        rs.getString("nome_tag"),

                        rs.getInt("quantidade")

                )

        );

    }

    public List<PlataformaGraficoDTO> buscarPlataformas() {

        String sql = """
        SELECT 'Windows' AS nome,
               COUNT(*) AS quantidade
        FROM Jogo
        WHERE windows = TRUE

        UNION ALL

        SELECT 'Mac',
               COUNT(*)
        FROM Jogo
        WHERE mac = TRUE

        UNION ALL

        SELECT 'Linux',
               COUNT(*)
        FROM Jogo
        WHERE linux = TRUE
        """;

        return jdbcTemplate.query(

                sql,

                (rs, row) -> new PlataformaGraficoDTO(

                        rs.getString("nome"),

                        rs.getInt("quantidade")

                )

        );

    }

}
