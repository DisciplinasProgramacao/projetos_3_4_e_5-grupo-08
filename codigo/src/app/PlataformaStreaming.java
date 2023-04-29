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

    public HashSet<Stream> getSeries() {
        return colecao;
    }

    public HashSet<Cliente> getClientes() {
        return clientes;
    }

    public Cliente login (String login){
        for(Cliente i : clientes) {
            if(i.getLogin() == login) {
                clienteAtual = i;
                return clienteAtual;
            }
        }
        return clienteAtual;
    }

    public void adicionarSerie(Serie serie){
        colecao.add(serie);
    }

    public void adicionarCliente(Cliente cliente){
        clientes.add(cliente);
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

    // public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios){
    //     List<Serie> result = new ArrayList<Serie>();
    //     for(Stream i : colecao) {
    //         if(i.getQuantidadeEpisodios() == quantEpisodios) {
    //             result.add(i);
    //         }
    //     }

    //     return result;
    // }

    public void registrarAudiencia(Serie serie){
    	clienteAtual.registrarAudiencia(serie);
        serie.registrarAudiencia(1);
    }
}
