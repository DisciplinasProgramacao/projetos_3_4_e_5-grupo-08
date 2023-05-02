package app;

public class AvaliacaoColecao {
    private Stream colecao;
    private double avaliacao;

    public AvaliacaoColecao(Stream colecao) {
        this.colecao = colecao;
    }

    public Stream getColecao() {
        return colecao;
    }

    public void setColecao(Stream colecao) {
        this.colecao = colecao;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
        this.colecao.avaliar(avaliacao);
    }
}
