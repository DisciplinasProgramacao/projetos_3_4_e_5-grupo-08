package app;
/**
 * Exceção: Película já avaliada
 */
public class PeliculaJaAvaliadaException extends Exception{
    /**
     * Esta exceção é lançada caso haja tentativa de avaliar uma película cuja já tenha sido avaliada previamente.
     * @param mensagem
     */
    public PeliculaJaAvaliadaException(String mensagem){
        super(mensagem);
    }
}
