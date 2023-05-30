package app;
/**
 * Exceção: Lista vazia
 */
public class ListaVaziaException extends Exception{
    /**
     * Esta exceção é lançada caso haja tentativa de visualizar uma lista que esteja vazia.
     * @param mensagem
     */
    public ListaVaziaException(String mensagem){
        super(mensagem);
    }
}
