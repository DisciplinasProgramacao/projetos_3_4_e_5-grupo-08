package app;

import java.util.Random;

public class Filme extends StreamAvaliavel{
    private float duracao;

    /**
     * Construtor da classe Filme
     * @param id
     * @param nome
     * @param genero
     * @param idioma
     * @param dataLancamento
     * @param duracao
     */
    public Filme(int id, String nome, int genero, int idioma, String dataLancamento, float duracao) {
        super(id, nome, genero, idioma, dataLancamento);
        this.duracao = duracao;
    }

    /**
     * Construtor da classe Filme
     * @param idFIlme
     * @param nome
     * @param dataDeLancamento
     * @param duracao
     */
    public Filme(int idFIlme, String nome, String dataDeLancamento, float duracao) {
        super(idFIlme, nome, dataDeLancamento);
        Random random = new java.util.Random();
        this.duracao = random.nextFloat() * 1000;
    }

    /**
     * Retorna a duracao do filme
     * 
     * @return float - duracao
     */
    public float getDuracao() {
        return this.duracao;
    }

    /**
     * Atribui uma duracao ao filme
     * 
     * @param duracao
     */
    public void setDuracao(float duracao) {
        this.duracao = duracao;
    }

    /**
     * Retorna uma String com os valores dos atributos
     * 
     * @return String
     */
    @Override
    public String toString() {
        String apresentacao = "Sobre o Filme: \n----------------------------------------------------------------\n";
        return apresentacao + super.toString() + "\nDuração: " + this.duracao + "\n\n";
    }
}
