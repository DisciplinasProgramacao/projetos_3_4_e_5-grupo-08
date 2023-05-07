package app;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

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
    public void login(String login, String senha) {
        for (Cliente i : clientes) {
            if (i.getLogin() == login) {
                if (i.getSenha() == senha) {
                    this.clienteAtual = i;
                }
            }
        }
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
            if (i.getLogin() == login) {
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
     * @return List<Stream> - result
     */
    public List<Stream> filtrarPorGenero(String genero) {
        List<Stream> result = new ArrayList<Stream>();
        for (Stream i : colecao) {
            if (i.getGenero() == genero) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * Filtra filmes e séries por idioma
     * 
     * @param idioma
     * @return List<Stream> - result
     */
    public List<Stream> filtrarPorIdioma(String idioma) {
        List<Stream> result = new ArrayList<Stream>();
        for (Stream i : colecao) {
            if (i.getIdioma() == idioma) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * Filtra filmes e séries por nome
     * 
     * @param nome
     * @return List<Stream> - result
     */
    public List<Stream> filtrarPorNome(String nome) {
        List<Stream> result = new ArrayList<Stream>();
        for (Stream i : colecao) {
            if (i.getNome() == nome) {
                result.add(i);
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
