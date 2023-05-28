package app;

public class PeliculaJaExistenteException extends Exception {
    public PeliculaJaExistenteException(String mensagem){
        super("A mídia já existe na lista: " + mensagem);
    }
}
