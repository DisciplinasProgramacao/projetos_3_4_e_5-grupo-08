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

    /**
     * Construtor da classe Avaliacao
     * @param colecao
     */
    public AvaliacaoColecao(Stream colecao) {
        this.colecao = colecao;
        this.avaliacao = -1;
    }

    /**
     * Retorna a colecao
     * 
     * @return Stream - colecao
     */
    public Stream getColecao() {
        return colecao;
    }
    
    /**
     * Atribui um valor a colecao
     * 
     * @param colecao
     */
    public void setColecao(Stream colecao) {
        this.colecao = colecao;
    }

    /**
     * Retorna a avaliacao
     * 
     * @return float - avaliacao
     */
    public float getAvaliacao() {
        return avaliacao;
    }

    /**
     * Atribui uma avaliação
     * 
     * @param avaliacao
     */
    public void setAvaliacao(float avaliacao) {
        this.avaliacao = avaliacao;
    }
}
