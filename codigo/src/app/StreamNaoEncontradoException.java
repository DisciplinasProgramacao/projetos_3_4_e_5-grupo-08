package app;
/**
 * Exceção: Stream não encontrado
 */
public class StreamNaoEncontradoException extends Exception {
    /**
     * Esta exceção é lançada caso haja tentativa de procura de película e a mesma não for encontrada.
     * @param mensagem
     */
    public StreamNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}