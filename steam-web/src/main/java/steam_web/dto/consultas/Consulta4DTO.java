package steam_web.dto.consultas;

public class Consulta4DTO {

    private String nome;
    private String genero;
    private double preco;
    private double precoMedio;

    public Consulta4DTO(String nome,
                        String genero,
                        double preco,
                        double precoMedio) {

        this.nome = nome;
        this.genero = genero;
        this.preco = preco;
        this.precoMedio = precoMedio;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public double getPreco() {
        return preco;
    }

    public double getPrecoMedio() {
        return precoMedio;
    }
}