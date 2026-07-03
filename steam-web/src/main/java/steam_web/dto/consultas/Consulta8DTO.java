package steam_web.dto.consultas;

public class Consulta8DTO {

    private String nomeJogo;
    private String tag;

    public Consulta8DTO(String nomeJogo, String tag) {
        this.nomeJogo = nomeJogo;
        this.tag = tag;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public String getTag() {
        return tag;
    }
}
