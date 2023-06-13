package app;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nomeDeUsuario;
    private String login;
    private String senha;
    List<StreamAvaliavel> listaParaVer;
    List<AvaliacaoStream> listaJaVistas;

    /**
     * Construtor com nome de usuario, login e senha. As listas são inicializadas
     * nesse método
     * 
     * @param nomeDeUsuario
     * @param login
     * @param senha
     */
    public Cliente(String nomeDeUsuario, String login, String senha) {
        setNomeDeUsuario(nomeDeUsuario);
        setLogin(login);
        setSenha(senha);
        this.listaParaVer = new ArrayList<StreamAvaliavel>();
        this.listaJaVistas = new ArrayList<AvaliacaoStream>();
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
    public String getLogin() {
        return this.login;
    }

    /**
     * Define um login para o cliente
     * 
     * @param login
     */
    public void setLogin(String login) {
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
    public void adicionarNaListaParaVer(StreamAvaliavel serieOuFilme) throws PeliculaJaExistenteException {
        if (!this.listaParaVer.contains(serieOuFilme)) {
            this.listaParaVer.add(serieOuFilme);
        } else {
            throw new PeliculaJaExistenteException("Para ver.");
        }

    }

    /**
     * Adiciona uma serie ou um filme na lista de assistidos
     * 
     * @param serieOuFilme
     */
    public void adicionarNaListaJaVisto(StreamAvaliavel serieOuFilme) throws PeliculaJaExistenteException {
        AvaliacaoStream novo = new AvaliacaoStream(serieOuFilme);
        if (!this.listaJaVistas.contains(novo)) {
            this.listaJaVistas.add(novo);
        } else {
            throw new PeliculaJaExistenteException("Já visto.");
        }

    }

    /**
     * Retira serie ou filme da lista para assistir
     * 
     * @param nomeSerieOuFilme
     */
    public void retirarDaLista(String nomeSerieOuFilme) {
        int index = 0;

        for (int i = 0; i < this.listaParaVer.size(); i++) {
            if (this.listaParaVer.get(i).getNome() == nomeSerieOuFilme)
                index = i;
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

        for (int i = 0; i < this.listaJaVistas.size(); i++) {
            if (this.listaJaVistas.get(i).getStream().getGenero() == genero)
                lista.add(this.listaJaVistas.get(i).getStream());
        }

        for (int i = 0; i < this.listaParaVer.size(); i++) {
            if (this.listaParaVer.get(i).getGenero() == genero)
                lista.add(this.listaParaVer.get(i));
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

        for (int i = 0; i < this.listaJaVistas.size(); i++) {
            if (this.listaJaVistas.get(i).getStream().getIdioma() == idioma)
                lista.add(this.listaJaVistas.get(i).getStream());
        }

        for (int i = 0; i < this.listaParaVer.size(); i++) {
            if (this.listaParaVer.get(i).getIdioma() == idioma)
                lista.add(this.listaParaVer.get(i));
        }

        return lista;
    }

    /**
     * Retorna a lista de filmes e séries já vistas
     * 
     * @return List<AvaliacaoStream>
     */
    public List<StreamAvaliavel> mostrarListaJaVista() throws ListaVaziaException {
        List<StreamAvaliavel> lista = new ArrayList<StreamAvaliavel>();

        for (AvaliacaoStream a : this.listaJaVistas) {
            lista.add(a.getStream());
        }
        if (lista.size() == 0) {
            throw new ListaVaziaException("Lista vazia.");
        } else {
            return lista;
        }

    }

    /**
     * Retorna a lista de filmes e série para assistir
     * 
     * @return
     */
    public List<StreamAvaliavel> mostrarListaParaAssistir() throws ListaVaziaException {
        if (listaParaVer.size() == 0) {
            throw new ListaVaziaException("Lista vazia.");
        } else {
            return listaParaVer;
        }

    }

    /**
     * Avalia um filme ou uma série ja vista
     * 
     * @param id
     * @param nota
     */
    public void avaliar(int id, float nota) throws PeliculaJaAvaliadaException {
        if (nota < 0)
            return;

        for (int i = 0; i < this.listaJaVistas.size(); i++) {
            if (this.listaJaVistas.get(i).getIdStream() == id) {
                if (!this.listaJaVistas.get(i).isAvaliado()) {
                    this.listaJaVistas.get(i).setAvaliacao(nota);
                } else {
                    throw new PeliculaJaAvaliadaException("Essa película já foi avaliada.");
                }
            }
        }
    }

    /**
     * Registra a audiência do cliente
     * 
     * Adiciona filme ou série na lista de assistidos
     * 
     * @param serieOuFilme
     */
    public void registrarAudiencia(StreamAvaliavel serieOuFilme) {

        try {
            this.adicionarNaListaJaVisto(serieOuFilme);
        } catch (PeliculaJaExistenteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Retorna uma lista com todas as séries ou filmes já avaliados
     * 
     * @return List<Stream> - lista
     */
    public List<Stream> avaliados() {
        List<Stream> listaAvaliados = new ArrayList<Stream>();

        for (AvaliacaoStream i : this.listaJaVistas) {
            if (i.getAvaliacao() != -1)
                listaAvaliados.add(i.getStream());
        }

        return listaAvaliados;
    }
}
