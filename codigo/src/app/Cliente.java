package app;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private String login;
    List<Serie> listaParaVer;
    List<Serie> listaJaVistas;


    public Cliente(String nomeDeUsuario, String login, String senha) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.login = login;
        this.listaParaVer = new ArrayList<Serie>();
        this.listaJaVistas =  new ArrayList<Serie>();
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public void adicionarNaLista(Serie serie) {
        this.listaParaVer.add(serie);
    }

    public void retirarDaLista(String nomeSerie) {
        int index = 0;

        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getNome() == nomeSerie) index = i;
        }

        this.listaParaVer.remove(index);
    }

    public List<Serie> filtrarPorGenero(String genero) {
        List<Serie> lista = new ArrayList<Serie>();

        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getGenero() == genero) lista.add(this.listaJaVistas.get(i));
        }

        for(int i = 0; i < this.listaParaVer.size(); i++) {
            if(this.listaParaVer.get(i).getGenero() == genero) lista.add(this.listaParaVer.get(i));
        }

        return lista;
    }

    public List<Serie> filtrarPorIdioma(String idioma) {
        List<Serie> lista = new ArrayList<Serie>();

        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getIdioma() == idioma) lista.add(this.listaJaVistas.get(i));
        }

        for(int i = 0; i < this.listaParaVer.size(); i++) {
            if(this.listaParaVer.get(i).getIdioma() == idioma) lista.add(this.listaParaVer.get(i));
        }

        return lista;
    }

    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        List<Serie> lista = new ArrayList<Serie>();

        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getQuantidadeEpisodios() == quantEpisodios) lista.add(this.listaJaVistas.get(i));
        }

        for(int i = 0; i < this.listaParaVer.size(); i++) {
            if(this.listaParaVer.get(i).getQuantidadeEpisodios() == quantEpisodios) lista.add(this.listaParaVer.get(i));
        }

        return lista;
    }

    public void registrarAudiencia(Serie serie) {
        this.listaJaVistas.add(serie);
    }
}
