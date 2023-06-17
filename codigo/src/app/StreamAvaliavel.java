package app;

import java.util.Random;

public abstract class StreamAvaliavel extends Stream {
    private int audiencia;
    private double avaliacao;
    private int contAvaliacao;

    /**
     * Construtor da classe Stream
     * @param id
     * @param nome
     * @param genero
     * @param idioma
     * @param dataLancamento
     */
    public StreamAvaliavel(int id, String nome, int genero, int idioma, String dataLancamento) {
        super(id, nome, genero, idioma, dataLancamento);
        this.audiencia = 0;
        Random random1 = new java.util.Random();
        this.avaliacao = random1.nextDouble() * 10 % 6;
        this.contAvaliacao = 1;
    }

    /**
     * Construtor classe Stream
     * 
     * @param id
     * @param nome
     * @param dataLancamento
     */
    public StreamAvaliavel(int id, String nome, String dataLancamento){
        super(id, nome, dataLancamento);
        this.audiencia = 0;
        Random random1 = new java.util.Random();
        this.avaliacao = random1.nextDouble() * 10 % 6;
        this.contAvaliacao = 1;
    }

    /**
     * Registra audiência, funciona como visualizações
     */
    public void registrarAudiencia(){
        setAudiencia((this.audiencia + 1));
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
     * Retorna a avaliacao media da midia
     * 
     * @return double - avaliacao
     */
    public double getAvaliacao() {
        return avaliacao;
    }

    public void avaliar(double nota) {
        double total = this.avaliacao * this.contAvaliacao;
        contAvaliacao++;
        double media = (nota + total)/this.contAvaliacao;
        this.avaliacao = media;
    }

    /**
     * Retorna uma String com os valores dos atributos
     * 
     * @return String
     */
    @Override
    public String toString() {
        String result =  super.toString() + "Audiência: " + this.audiencia + "\n" + "Avaliação: " + this.avaliacao + "\n";
        return result;
    }

    /**
     * Retorna o número de avaliações.
     *
     * @return o número de avaliações
     */
    public int getNumeroDeAvaliacoes() {
        return numeroDeAvaliacoes;
    }

    /**
     * Retorna a média das avaliações.
     *
     * @return a média das avaliações
     */
    public double getMediaDeAvaliacoes() {
        if (numeroDeAvaliacoes == 0) {
            return 0.0;
        } else {
            return somaDasAvaliacoes / numeroDeAvaliacoes;
        }
    }

    /**
     * Retorna o número de visualizações.
     *
     * @return o número de visualizações
     */
    public int getNumeroDeVisualizacoes() {
        return numeroDeVisualizacoes;
    }

    /**
     * Incrementa o número de visualizações.
     */
    public void incrementarNumeroDeVisualizacoes() {
        numeroDeVisualizacoes++;
    }
}
