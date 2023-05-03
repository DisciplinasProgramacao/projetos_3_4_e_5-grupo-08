package app;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class PlataformaStreaming {

    private String nome;
    private HashSet<Stream> colecao;
    private HashSet<Cliente> clientes;
    private Cliente clienteAtual;

    public PlataformaStreaming(String nome) {
        this.nome = nome;
        this.colecao = new HashSet<Stream>();
        this.clientes = new HashSet<Cliente>();
        this.clienteAtual = null;
    }

    public String getNome() {
        return nome;
    }

    public HashSet<Stream> getColecao() {
        return colecao;
    }

    public HashSet<Cliente> getClientes() {
        return clientes;
    }

    public void login (String login, String senha){
        for(Cliente i : clientes) {
            if(i.getLogin() == login){
                if (i.getSenha() == senha){
                    this.clienteAtual = i;
                }
            }        
        }
    }  

    public void adicionarColecao(Stream novo){
        colecao.add(novo);
    }

    public void adicionarCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public Cliente getClienteAtual(){
        return this.clienteAtual;
    }

    public String getNomeClienteAtual(){
        return clienteAtual.getNomeDeUsuario();
    }

    public List<Stream> filtrarPorGenero(String genero){
        List<Stream> result = new ArrayList<Stream>();
        for(Stream i : colecao) {
            if(i.getGenero() == genero) {
                result.add(i);
            }
        }

        return result;
    }

    public List<Stream> filtrarPorIdioma(String idioma){
        List<Stream> result = new ArrayList<Stream>();
        for(Stream i : colecao) {
            if(i.getIdioma() == idioma) {
                result.add(i);
            }
        }

        return result;
    }

    public List<Stream> filtrarPorNome(String nome){
        List<Stream> result = new ArrayList<Stream>();
        for(Stream i : colecao) {
            if(i.getNome() == nome) {
                result.add(i);
            }
        }

        return result;
    }

    public void registrarAudiencia(Stream serieOuFilme){
        serieOuFilme.setAudiencia(1);
    }
}
