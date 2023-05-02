package app;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nomeDeUsuario;
    private String login;
    private String senha;
    List<Stream> listaParaVer;
    List<AvaliacaoColecao> listaJaVistas;


    public Cliente(String nomeDeUsuario,String login, String senha) {
        setNomeDeUsuario(nomeDeUsuario);
        setLogin(login);
        setSenha(senha);
        this.listaParaVer = new ArrayList<Stream>();
        this.listaJaVistas =  new ArrayList<AvaliacaoColecao>();
    }

    public String getNomeDeUsuario() {
        return this.nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getLogin(){
        return this.login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void adicionarNaListaParaVer(Stream serieOuFilme) {
        this.listaParaVer.add(serieOuFilme);
    }

    public void adicionarNaListaJaVisto(Stream serieOuFilme) {
        AvaliacaoColecao novo = new AvaliacaoColecao(serieOuFilme);
        this.listaJaVistas.add(novo);
    }

    public void retirarDaLista(String nomeSerie) {
        int index = 0;

        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getColecao().getNome() == nomeSerie) index = i;
        }

        this.listaParaVer.remove(index);
    }

    public List<Stream> filtrarPorGenero(String genero) {
        List<Stream> lista = new ArrayList<Stream>();

        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getColecao().getGenero() == genero) lista.add(this.listaJaVistas.get(i).getColecao());
        }

        for(int i = 0; i < this.listaParaVer.size(); i++) {
            if(this.listaParaVer.get(i).getGenero() == genero) lista.add(this.listaParaVer.get(i));
        }

        return lista;
    }

    public List<Stream> filtrarPorIdioma(String idioma) {
        List<Stream> lista = new ArrayList<Stream>();

        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getColecao().getIdioma() == idioma) lista.add(this.listaJaVistas.get(i).getColecao());
        }

        for(int i = 0; i < this.listaParaVer.size(); i++) {
            if(this.listaParaVer.get(i).getIdioma() == idioma) lista.add(this.listaParaVer.get(i));
        }

        return lista;
    }

    public List<AvaliacaoColecao> mostrarListaJaVista(){
        return listaJaVistas;
    }

    public List<Stream> mostrarListaParaAssistir(){
        return listaParaVer;
    }

    public void avaliar(int id, float nota) {
        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getColecao().getId() == id) this.listaJaVistas.get(i).setAvaliacao(nota);
        }
    }
}
