package steam_web.dto.graficos;

public class IdiomaGraficoDTO {

    private String nome;
    private int quantidade;

    public IdiomaGraficoDTO(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
