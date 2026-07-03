package steam_web.dto.consultas;

public class Consulta9DTO {

    private String nome;
    private double preco;

    public Consulta9DTO(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
