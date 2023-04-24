package app;

public class Filme {
    private int idFIlme;
    private String nome;
    private String dataDeLancamento;
    private float duracao;

    public Filme(int idFIlme, String nome, String dataDeLancamento, float duracao) {
        this.idFIlme = idFIlme;
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
        this.duracao = duracao;
    }

    public int getIdFIlme() {
        return this.idFIlme;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataDeLancamento() {
        return this.dataDeLancamento;
    }

    public void setDataDeLancamento(String dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    public float getDuracao() {
        return this.duracao;
    }

    public void setDuracao(float duracao) {
        this.duracao = duracao;
    }

}
