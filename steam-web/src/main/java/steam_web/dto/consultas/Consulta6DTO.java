package steam_web.dto.consultas;

public class Consulta6DTO {

    private String tag;
    private String jogo;

    public Consulta6DTO(String tag, String jogo) {
        this.tag = tag;
        this.jogo = jogo;
    }

    public String getTag() {
        return tag;
    }

    public String getJogo() {
        return jogo;
    }
}
