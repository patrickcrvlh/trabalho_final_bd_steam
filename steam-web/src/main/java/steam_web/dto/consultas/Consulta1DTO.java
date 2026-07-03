package steam_web.dto.consultas;

public class Consulta1DTO {

    private String jogo;
    private String genero;

    public Consulta1DTO(String jogo, String genero) {
        this.jogo = jogo;
        this.genero = genero;
    }

    public String getJogo() {
        return jogo;
    }

    public String getGenero() {
        return genero;
    }
}
