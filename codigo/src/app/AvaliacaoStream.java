package app;
/**
 * Classe AvaliacaoStream
 * 
 * Permite armazenar um filme ou serie junto com uma avaliação
 * a avaliação é realizada pelo usuário
 */
public class AvaliacaoStream {
    private Stream stream;
    private float avaliacao;

    /**
     * Construtor da classe Avaliacao
     * @param stream
     */
    public AvaliacaoStream(Stream stream) {
        this.stream = stream;
        this.avaliacao = -1;
    }

    /**
     * Retorna a stream
     * 
     * @return Stream - stream
     */
    public Stream getStream() {
        return stream;
    }
    
    /**
     * Atribui um valor a stream
     * 
     * @param stream
     */
    public void setStream(Stream stream) {
        this.stream = stream;
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

    /**
     * Retorna o id do stream
     * 
     * @return int - stream.id
     */
    public int getIdStream() {
        return this.stream.getId();
    }

    /**
     * Retorna se a midia ja foi avaliada
     * 
     * @return boolean
     */
    public boolean isAvaliado() {
        return this.avaliacao != -1;
    }
}
