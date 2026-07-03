package steam_web.dto.consultas;

public class Consulta3DTO {

    private String genero;
    private int quantidade;

    public Consulta3DTO(String genero, int quantidade) {
        this.genero = genero;
        this.quantidade = quantidade;
    }

    public String getGenero() {
        return genero;
    }

    public int getQuantidade() {
        return quantidade;
    }
}