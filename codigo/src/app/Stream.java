package app;

import java.util.Random;

public abstract class Stream {
    public static int contId = 0;

    private int id;
    private String nome;
    private String genero;
    private String idioma;
    private String dataLancamento;

    public static final String[] generos = {
        "Comedia",
        "Romance",
        "Ação",
        "Anime",
        "Aventura",
        "Documentário",
        "Drama",
        "Policial",
        "Suspense"
    };

    public static final String[] idiomas = {
        "Ingles",
        "Portugues"
    };

    /**
     * Construtor da classe Stream
     * @param id
     * @param nome
     * @param genero
     * @param idioma
     * @param dataLancamento
     */
    public Stream(int id, String nome, int genero, int idioma, String dataLancamento) {
        if(id > contId) {
            contId = id;
        }
        this.id = id;
        this.nome = nome;
        this.genero = generos[genero];
        this.idioma = idiomas[idioma];
        this.dataLancamento = dataLancamento;
    }

    /**
     * Construtor classe Stream
     * 
     * @param id
     * @param nome
     * @param dataLancamento
     */
    public Stream(int id, String nome, String dataLancamento){
        if(id > contId) {
            contId = id;
        }
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;

        Random random = new java.util.Random();
        int generoAleatorio = random.nextInt(generos.length);

        this.genero = generos[generoAleatorio];

        Random random1 = new java.util.Random();
        int idiomaAleatorio = random1.nextInt(idiomas.length);

        this.idioma = idiomas[idiomaAleatorio];
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

    /**
     * Retorna uma String com os valores dos atributos
     * 
     * @return String
     */
    @Override
    public String toString() {
        String result = "Id: " + this.id + "\n" + "Nome: " + this.nome + "\n" + "Gênero: " + this.genero + "\n" + "Idioma: " + this.idioma + "\n" + "Data: " + this.dataLancamento + "\n";
        return result;
    }
}
