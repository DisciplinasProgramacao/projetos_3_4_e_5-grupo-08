package app;

public class Serie extends Stream {
    private int quantidadeEpisodios;
    
    public Serie(int IdSerie, String nome, String dataDeLancamento){
        super(IdSerie, nome, dataDeLancamento);
    }

    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        this.quantidadeEpisodios = quantidadeEpisodios;
    }
}
