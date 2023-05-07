package app;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nomeDeUsuario;
    private String login;
    private String senha;
    List<Stream> listaParaVer;
    List<AvaliacaoColecao> listaJaVistas;

    /**
     * Construtor com nome de usuario, login e senha. As listas são inicializadas nesse método
     * @param nomeDeUsuario
     * @param login
     * @param senha
     */
    public Cliente(String nomeDeUsuario,String login, String senha) {
        setNomeDeUsuario(nomeDeUsuario);
        setLogin(login);
        setSenha(senha);
        this.listaParaVer = new ArrayList<Stream>();
        this.listaJaVistas =  new ArrayList<AvaliacaoColecao>();
    }

    /**
     * Retorna o nome de usuario do cliente
     * 
     * @return String - noomeDeUsuario
     */
    public String getNomeDeUsuario() {
        return this.nomeDeUsuario;
    }

    /**
     * Define um nome de usuario ao cliente
     * 
     * @param nomeDeUsuario
     */
    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    /**
     * Retorna o login do cliente
     * 
     * @return String - login
     */
    public String getLogin(){
        return this.login;
    }

    /**
     * Define um login para o cliente
     * 
     * @param login
     */
    public void setLogin(String login){
        this.login = login;
    }

    /**
     * Retorna a senha do cliente
     * 
     * @return String - senha
     */
    public String getSenha() {
        return this.senha;
    }

    /**
     * Define uma senha para o cliente
     * 
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Adiciona uma serie ou um filme na lista para assistir
     * 
     * @param serieOuFilme
     */
    public void adicionarNaListaParaVer(Stream serieOuFilme) {
        this.listaParaVer.add(serieOuFilme);
    }

    /**
     * Adiciona uma serie ou um filme na lista de assistidos
     * 
     * @param serieOuFilme
     */
    public void adicionarNaListaJaVisto(Stream serieOuFilme) {
        AvaliacaoColecao novo = new AvaliacaoColecao(serieOuFilme);
        this.listaJaVistas.add(novo);
    }

    /**
     * Retira serie ou filme da lista para assistir
     * @param nomeSerie
     */
    public void retirarDaLista(String nomeSerie) {
        int index = 0;

        for(int i = 0; i < this.listaParaVer.size(); i++) {
            if(this.listaParaVer.get(i).getNome() == nomeSerie) index = i;
        }

        this.listaParaVer.remove(index);
    }

    /**
     * Filtra filmes e séries por genero
     * 
     * @param genero
     * @return List<Stream>
     */
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

    /**
     * Filtra filmes e séries por idioma
     * 
     * @param idioma
     * @return List<Stream>
     */
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

    /**
     * Retorna a lista de filmes e séries já vistas
     * @return List<AvaliacaoColecao>
     */
    public List<AvaliacaoColecao> mostrarListaJaVista(){
        return listaJaVistas;
    }

    /**
     * Retorna a lista de filmes e série para assistir
     * @return
     */
    public List<Stream> mostrarListaParaAssistir(){
        return listaParaVer;
    }

    /**
     * Avalia um filme ou uma série ja vista
     * 
     * @param id
     * @param nota
     */
    public void avaliar(int id, float nota) {
        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getColecao().getId() == id) this.listaJaVistas.get(i).setAvaliacao(nota);
        }
    }

    /**
     * Registra a audiência do cliente
     * 
     * Adiciona filme ou série na lista de assistidos
     * @param serieOuFilme
     */
    public void registrarAudiencia(Stream serieOuFilme) {
        this.adicionarNaListaJaVisto(serieOuFilme);
    }
}
