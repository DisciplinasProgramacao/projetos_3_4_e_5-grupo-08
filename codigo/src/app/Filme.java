package app;

public class Filme extends Stream{
    private float duracao;

    public Filme(int idFIlme, String nome, String dataDeLancamento, float duracao) {
        super(idFIlme, nome, dataDeLancamento);
    }

    public float getDuracao() {
        return this.duracao;
    }

    public void setDuracao(float duracao) {
        this.duracao = duracao;
    }

}
