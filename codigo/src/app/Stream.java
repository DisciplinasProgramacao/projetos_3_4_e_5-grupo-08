package app;

import java.util.Random;

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

    public Stream(int id, String nome, String genero, String idioma, String dataLancamento) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.dataLancamento = dataLancamento;
        this.audiencia = 0;
    }

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

    public int getId() {
        return id;
    }

    public void registrarAudiencia(int qnt){
        setAudiencia((this.audiencia + qnt));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getAudiencia() {
        return audiencia;
    }

    public void setAudiencia(int audiencia) {
        this.audiencia = audiencia;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

}
