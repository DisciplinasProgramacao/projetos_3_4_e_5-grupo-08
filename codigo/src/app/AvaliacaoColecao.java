package app;
/**
 * Classe AvaliacaoColecao
 * 
 * Permite armazenar um filme ou serie junto com uma avaliação
 * a avaliação é realizada pelo usuário
 */
public class AvaliacaoColecao {
    private Stream colecao;
    private float avaliacao;

    public AvaliacaoColecao(Stream colecao) {
        this.colecao = colecao;
    }

    public Stream getColecao() {
        return colecao;
    }

    public void setColecao(Stream colecao) {
        this.colecao = colecao;
    }

    public float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(float avaliacao) {
        this.avaliacao = avaliacao;
    }
}
