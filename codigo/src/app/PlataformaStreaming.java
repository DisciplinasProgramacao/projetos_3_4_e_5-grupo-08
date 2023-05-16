package app;

import java.util.HashSet;

public class PlataformaStreaming {

    private String nome;
    private HashSet<Stream> colecao;
    private HashSet<Cliente> clientes;
    private Cliente clienteAtual;

    /**
     * Construtor da Plataforma
     * 
     * @param nome
     */
    public PlataformaStreaming(String nome) {
        this.nome = nome;
        this.colecao = new HashSet<Stream>();
        this.clientes = new HashSet<Cliente>();
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
     * @return HashSet<Stream> - colecao
     */
    public HashSet<Stream> getColecao() {
        return colecao;
    }

    /**
     * Retorna o Hash de Clientes
     * 
     * @return HashSet<Cliente> - clientes
     */
    public HashSet<Cliente> getClientes() {
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
        for (Cliente i : clientes) {
            if (i.getLogin().equals(login)) {
                if (i.getSenha().equals(senha)) {
                    this.clienteAtual = i;
                    return true;
                }
            }
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
        for (Cliente i : clientes) {
            if (i.getLogin().equals(login)) {
                this.clienteAtual = i;
            }
        }
    }

    /**
     * Adiciona filme ou série no hash da coleção da plataforma
     * 
     * @param novo
     */
    public void adicionarColecao(Stream novo) {
        colecao.add(novo);
    }

    /**
     * Adiciona um cliente na plataforma
     * 
     * @param cliente
     */
    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
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
    public Stream filtrarPorGenero(String genero) {
        Stream result = null;
        for (Stream i : colecao) {
            if (i.getGenero() == genero) {
                result = i;
                break;
            }
        }

        return result;
    }

    /**
     * Filtra filmes e séries por idioma
     * 
     * @param idioma
     * @return Stream - result
     */
    public Stream filtrarPorIdioma(String idioma) {
        Stream result = null;
        for (Stream i : colecao) {
            if (i.getIdioma() == idioma) {
                result = i;
                break;
            }
        }

        return result;
    }

    /**
     * Filtra filmes e séries por nome
     * 
     * @param nome
     * @return Stream - result
     */
    public Stream filtrarPorNome(String nome) {
        Stream result = null;
        for (Stream i : colecao) {
            String n = i.getNome();
            if (n.equals(nome)) {
                result = i;
                break;
            }
        }

        return result;
    }

    /**
     * Registra audiência caso o cliente atual assista uma série
     * 
     * @param serieOuFilme
     */
    public void registrarAudiencia(Stream serieOuFilme) {
        this.clienteAtual.registrarAudiencia(serieOuFilme);
        serieOuFilme.registrarAudiencia();
    }

    public Stream encontraStreamPorId(int id) {
        for (Stream i : this.colecao) {
            if (i.getId() == id) {
                return i;
            }
        }

        return null;
    }
}
