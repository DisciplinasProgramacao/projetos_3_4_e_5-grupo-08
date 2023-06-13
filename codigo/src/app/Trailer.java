package app;

public class Trailer extends Stream {
    /**
     * Construtor da classe Stream
     * @param id
     * @param nome
     * @param genero
     * @param idioma
     * @param dataLancamento
     */
    public Trailer(int id, String nome, int genero, int idioma, String dataLancamento) {
        super(id, nome, genero, idioma, dataLancamento);
    }

    /**
     * Construtor classe Stream
     * 
     * @param id
     * @param nome
     * @param dataLancamento
     */
    public Trailer(int id, String nome, String dataLancamento){
        super(id, nome, dataLancamento);
    }
}
