package steam_web.dto;

public class EngajamentoDTO {

    private String nome;

    private int engajamento;

    public EngajamentoDTO() {
    }

    public EngajamentoDTO(String nome, int engajamento) {
        this.nome = nome;
        this.engajamento = engajamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEngajamento() {
        return engajamento;
    }

    public void setEngajamento(int engajamento) {
        this.engajamento = engajamento;
    }
}
