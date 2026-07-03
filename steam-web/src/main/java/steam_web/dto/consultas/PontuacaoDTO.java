package steam_web.dto.consultas;

public class PontuacaoDTO {

    private String nome;
    private int totalReviews;

    public PontuacaoDTO(String nome, int totalReviews) {
        this.nome = nome;
        this.totalReviews = totalReviews;
    }

    public String getNome() {
        return nome;
    }

    public int getTotalReviews() {
        return totalReviews;
    }
}