package steam_web.dto;

public class JogoDTO {

    private int appId;
    private String nome;
    private double preco;
    private int totalReviews;

    public JogoDTO(int appId,
                   String nome,
                   double preco,
                   int totalReviews) {

        this.appId = appId;
        this.nome = nome;
        this.preco = preco;
        this.totalReviews = totalReviews;
    }

    public int getAppId() {
        return appId;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getTotalReviews() {
        return totalReviews;
    }
}
