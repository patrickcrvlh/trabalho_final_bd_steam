package steam_web.dto.consultas;

public class Consulta2DTO {

    private String jogo;
    private String categoria;
    private String idioma;

    public Consulta2DTO(String jogo, String categoria, String idioma) {
        this.jogo = jogo;
        this.categoria = categoria;
        this.idioma = idioma;
    }

    public String getJogo() {
        return jogo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getIdioma() {
        return idioma;
    }
}
