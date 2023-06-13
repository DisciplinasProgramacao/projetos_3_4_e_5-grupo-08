package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlataformaStreaming {

    private String nome;
    private HashMap<Integer, Stream> colecao;
    private HashMap<String, Cliente> clientes;
    private Cliente clienteAtual;

    /**
     * Construtor da Plataforma
     * 
     * @param nome
     */
    public PlataformaStreaming(String nome) {
        this.nome = nome;
        this.colecao = new HashMap<Integer, Stream>();
        this.clientes = new HashMap<String, Cliente>();
        this.clienteAtual = null;
    }

    /**
     * Retorna o nome da Plataforma de Straming
     * 
     * @return String - nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o Hash de Series e Filmes
     * 
     * @return HashMap<Integer, Stream> - colecao
     */
    public HashMap<Integer, Stream> getColecao() {
        return colecao;
    }

    /**
     * Retorna o Hash de Clientes
     * 
     * @return HashMap<String, Cliente> - clientes
     */
    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    /**
     * Atribui o cliente atual referente ao login e senha enviados
     * 
     * Faz o login na plataforma
     * 
     * @param login
     * @param senha
     */
    public boolean loginPlataforma(String login, String senha) {
        try{
         if (clientes.get(login).getLogin().equals(login)){
            if(clientes.get(login).getSenha().equals(senha)){
                this.clienteAtual = clientes.get(login);
                return true;
                }
            }   
        } catch (NullPointerException e) {
            // Trata a exceção NullPointerException (login não encontrado no mapa)
            System.out.println("Login não encontrado.");
        }
        
        return false;
    }

    /**
     * Atribui o cliente atual referente ao login enviado
     * 
     * Faz o login na plataforma
     * 
     * @param login
     */
    public void login(String login) {
        this.clienteAtual = clientes.get(login);
    }

    /**
     * Adiciona filme ou série no hash da coleção da plataforma
     * 
     * @param novo
     */
    public void adicionarColecao(Stream novo) {
        colecao.put(novo.getId(), novo);
    }

    /**
     * Adiciona um cliente na plataforma
     * 
     * @param cliente
     */
    public void adicionarCliente(Cliente cliente) {
        clientes.put(cliente.getLogin(), cliente);
    }

    /**
     * Retorna o cliente logado no momento
     * 
     * @return Cliente -clienteAtual
     */
    public Cliente getClienteAtual() {
        return this.clienteAtual;
    }

    /**
     * Retorna o nome do cliente atual
     * 
     * @return String - clienteAtual.nome
     */
    public String getNomeClienteAtual() {
        return clienteAtual.getNomeDeUsuario();
    }

    /**
     * Filtra filmes e séries por gênero
     * 
     * @param genero
     * @return Stream - result
     */
    public List<Stream> filtrarPorGenero(String genero) throws StreamNaoEncontradoException{
        List<Stream> result = new ArrayList<Stream>();
        
        for (Stream i : colecao.values()) {
            if (i.getGenero().equals(genero)) {
                result.add(i);
            }
        }
        if (result.size() == 0){
            throw new StreamNaoEncontradoException("Não foram encontradas midias desse gênero");
        }

        return result;
    }

    /**
     * Filtra filmes e séries por idioma
     * 
     * @param idioma
     * @return Stream - result
     */
    public List<Stream> filtrarPorIdioma(String idioma) throws StreamNaoEncontradoException {
        List<Stream> result = new ArrayList<Stream>();
        for (Stream i : colecao.values()) {
            if (i.getIdioma().equals(idioma)) {
                result.add(i);
            }
        }
        if (result.size() == 0){
            throw new StreamNaoEncontradoException("Não foram encontradas midias desse idioma");
        }

        return result;
    }

    /**
     * Filtra filmes e séries por nome
     * 
     * @param nome
     * @return Stream - result
     */
    public Stream filtrarPorNome(String nome) throws StreamNaoEncontradoException{
        Stream result = null;
        for (Stream i : colecao.values()) {
            String n = i.getNome();
            if (n.equals(nome)) {
                result = i;
                break;
            }
        }
        if (result == null){
            throw new StreamNaoEncontradoException("Não foi encontrada uma midia com o nome");
        }
        return result;
    }

    /**
     * Registra audiência caso o cliente atual assista uma série
     * 
     * @param serieOuFilme
     */
    public void registrarAudiencia(StreamAvaliavel serieOuFilme) {
        this.clienteAtual.registrarAudiencia(serieOuFilme);
        serieOuFilme.registrarAudiencia();
    }

    /**
     * Retorna a midia a partir do ID
     * 
     * @param id
     * @return Stream
     */
    public Stream encontraStreamPorId(int id) {
        return colecao.get(id);
    }

    /**
     * Adiciona uma mídia na lista para assistir do cliente logado
     * @param stream
     * @throws PeliculaJaExistenteException
     */
    public void adicionarNaListaParaVer(StreamAvaliavel stream) throws PeliculaJaExistenteException {
        this.clienteAtual.adicionarNaListaParaVer(stream);
    }

    /**
     * Avalia uma midia do cliente
     * 
     * @param inserirId
     * @param inserirNota
     * @throws PeliculaJaAvaliadaException
     */
    public void avaliar(int inserirId, float inserirNota) throws PeliculaJaAvaliadaException {
        this.clienteAtual.avaliar(inserirId, inserirNota);
    }

    /**
     * Método genério que imprime uma lista de midias
     * @param lista
     */
    public void mostrarListaStream(List<Stream> lista) {
        for(Stream s : lista) {
            System.out.println(s);
        }
    }

    public void mostrarLista(List<StreamAvaliavel> lista) {
        for(StreamAvaliavel s : lista) {
            System.out.println(s);
        }
    }
    
    /**
     * Metodo que retorna a lista de midias para assistir do cliente
     * 
     * @return
     * @throws ListaVaziaException
     */
    public List<StreamAvaliavel> mostrarListaParaAssistir() throws ListaVaziaException {
        return this.clienteAtual.mostrarListaParaAssistir();
    }

    /**
     * Metodo que retorna a lista de midias ja vistas do cliente
     * @return
     * @throws ListaVaziaException
     */
    public List<StreamAvaliavel> mostrarListaJaVista() throws ListaVaziaException{
        return this.clienteAtual.mostrarListaJaVista();
    }
}
