package app;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private String login;
    List<Stream> listaParaVer;
    List<Stream> listaJaVistas;


    public Cliente(String nomeDeUsuario, String senha, String login) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.login = login;
        this.listaParaVer = new ArrayList<Stream>();
        this.listaJaVistas =  new ArrayList<Stream>();
    }

    public String getNomeDeUsuario() {
        return this.nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void adicionarNaListaParaVer(Serie serie) {
        this.listaParaVer.add(serie);
    }

    public void adicionarNaListaJaVisto(Serie serie) {
        this.listaJaVistas.add(serie);
    }

    public void retirarDaLista(String nome) {
        int index = 0;

        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getNome() == nome) index = i;
        }

        this.listaParaVer.remove(index);
    }

    public List<Stream> filtrarPorGenero(String genero) {
        List<Stream> lista = new ArrayList<Stream>();

        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getGenero() == genero) lista.add(this.listaJaVistas.get(i));
        }

        for(int i = 0; i < this.listaParaVer.size(); i++) {
            if(this.listaParaVer.get(i).getGenero() == genero) lista.add(this.listaParaVer.get(i));
        }

        return lista;
    }

    public List<Stream> filtrarPorIdioma(String idioma) {
        List<Stream> lista = new ArrayList<Stream>();

        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getIdioma() == idioma) lista.add(this.listaJaVistas.get(i));
        }

        for(int i = 0; i < this.listaParaVer.size(); i++) {
            if(this.listaParaVer.get(i).getIdioma() == idioma) lista.add(this.listaParaVer.get(i));
        }

        return lista;
    }

    // public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
    //     List<Serie> lista = new ArrayList<Serie>();

    //     for(int i = 0; i < this.listaJaVistas.size(); i++) {
    //         if(this.listaJaVistas.get(i).getQuantidadeEpisodios() == quantEpisodios) lista.add(this.listaJaVistas.get(i));
    //     }

    //     for(int i = 0; i < this.listaParaVer.size(); i++) {
    //         if(this.listaParaVer.get(i).getQuantidadeEpisodios() == quantEpisodios) lista.add(this.listaParaVer.get(i));
    //     }

    //     return lista;
    // }

    public void registrarAudiencia(Serie serie) {
        this.listaJaVistas.add(serie);
    }

    public List<Stream> mostrarListaJaVista(){
        return listaJaVistas;
    }

    public List<Stream> mostrarListaParaAssistir(){
        return listaParaVer;
    }
}
