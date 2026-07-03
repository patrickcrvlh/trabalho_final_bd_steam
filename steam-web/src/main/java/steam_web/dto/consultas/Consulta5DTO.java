package steam_web.dto.consultas;

public class Consulta5DTO {

    private String nome;
    private int recomendacoes;

    public Consulta5DTO(String nome, int recomendacoes) {
        this.nome = nome;
        this.recomendacoes = recomendacoes;
    }

    public String getNome() {
        return nome;
    }

    public int getRecomendacoes() {
        return recomendacoes;
    }
}
