package app;

public class Serie extends Stream {
    private int quantidadeEpisodios;

    /**
     * Construtor da classe Serie
     * @param id
     * @param nome
     * @param genero
     * @param idioma
     * @param dataLancamento
     * @param quantidadeEpisodios
     */
    public Serie(int id, String nome, String genero, String idioma, String dataLancamento, int quantidadeEpisodios) {
        super(id, nome, genero, idioma, dataLancamento);
        this.quantidadeEpisodios = quantidadeEpisodios;
    }
    
    /**
     * Construtor da classe Serie
     * @param IdSerie
     * @param nome
     * @param dataDeLancamento
     */
    public Serie(int IdSerie, String nome, String dataDeLancamento){
        super(IdSerie, nome, dataDeLancamento);
    }

    /**
     * Retorna a quantidade de episodios da série 
     * 
     * @return int - quantidadeEpisodios
     */
    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    /**
     * Atribui uma quantidade de episodios para a serie
     * @param quantidadeEpisodios
     */
    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

}
