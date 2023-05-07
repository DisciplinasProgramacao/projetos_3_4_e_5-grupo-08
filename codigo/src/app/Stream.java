package app;

import java.util.Random;

//Classe Stream (pai de series filmes)
public abstract class Stream {
    private int id;
    private String nome;
    private String genero;
    private String idioma;
    private int audiencia;
    private String dataLancamento;

    //GENEROS
    public static final String TERROR = "Terror";
    public static final String COMEDIA = "Comedia";
    public static final String ROMANCE = "Romance";
    public static final String FICCAO = "Ficcao";

    //IDIOMAS
    public static final String INGLES = "Ingles";
    public static final String PORTUGUES = "Portugues";

    /**
     * Construtor da classe Stream
     * @param id
     * @param nome
     * @param genero
     * @param idioma
     * @param dataLancamento
     */
    public Stream(int id, String nome, String genero, String idioma, String dataLancamento) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.dataLancamento = dataLancamento;
        this.audiencia = 0;
    }

    /**
     * Construtor classe Stream
     * 
     * @param id
     * @param nome
     * @param dataLancamento
     */
    public Stream(int id, String nome, String dataLancamento){
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.audiencia = 0;

        String[] generos = {TERROR, COMEDIA, FICCAO, ROMANCE};
        Random random = new java.util.Random();
        int generoAleatorio = random.nextInt(generos.length);

        this.genero = generos[generoAleatorio];

        String[] idiomas = {PORTUGUES, INGLES};
        Random random1 = new java.util.Random();
        int idiomaAleatorio = random1.nextInt(idiomas.length);

        this.idioma = idiomas[idiomaAleatorio];
    }

    /**
     * Registra audiência, funciona como visualizações
     */
    public void registrarAudiencia(){
        setAudiencia((this.audiencia + 1));
    }

    /**
     * Retorna o Id do Stream
     * 
     * @return int - id
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna o nome do Stream
     * 
     * @return String - nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Atribui um nome ao Stream
     * 
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o genero do Stream
     * 
     * @return String - genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Atribui genero ao Stream
     * 
     * @param genero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Retorna o idioma do Stream
     * 
     * @return String - idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Atribui idioma ao Stream
     * 
     * @param idioma
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * Retorna a audiência do Stream
     * 
     * @return int - audiencia
     */
    public int getAudiencia() {
        return audiencia;
    }

    /**
     * Atribui um valor a audiência do Stream
     * @param audiencia
     */
    public void setAudiencia(int audiencia) {
        this.audiencia = audiencia;
    }

    /**
     * Retorna a data de lançamento do Stream
     * 
     * @return String - dataLancamento
     */
    public String getDataLancamento() {
        return dataLancamento;
    }

    /**
     * Atribui uma data de lancamento ao Stream
     * 
     * @param dataLancamento
     */
    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

}
