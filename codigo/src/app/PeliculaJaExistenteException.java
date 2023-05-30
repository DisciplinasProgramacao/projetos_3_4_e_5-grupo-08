package app;
/**
 * Exceção: Película já existente
 */
public class PeliculaJaExistenteException extends Exception {
    /**
     * Esta exceção é lançada caso haja uma tentativa de adicionar uma película já existente em uma lista.
     * @param mensagem
     */
    public PeliculaJaExistenteException(String mensagem){
        super("A mídia já existe na lista: " + mensagem);
    }
}
