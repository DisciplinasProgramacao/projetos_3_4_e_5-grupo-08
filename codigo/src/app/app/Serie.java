package app;

public class Serie {
    String[] GENEROS;
    String nome;
    String genero;
    String idioma;
    int quantidadeEpisodios;
    int audiencia;
    
    public Serie(String nome, String genero, String idioma, int quantidadeEpisodios, int audiencia){
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.quantidadeEpisodios = quantidadeEpisodios;
        this.audiencia = audiencia;
    }
    
    public void registrarAudiencia(int qnt){
        setAudiencia(qnt);
    }



    public String[] getGENEROS() {
        return GENEROS;
    }

    public void setGENEROS(String[] gENEROS) {
        GENEROS = gENEROS;
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

    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    public int getAudiencia() {
        return audiencia;
    }

    public void setAudiencia(int audiencia) {
        this.audiencia = audiencia;
    }
}
