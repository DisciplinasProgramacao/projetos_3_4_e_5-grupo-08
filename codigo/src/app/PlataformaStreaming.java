package app;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class PlataformaStreaming {

    private String nome;
    private HashSet<Serie> series;
    private HashSet<Filme> filmes;
    private HashSet<Cliente> clientes;
    private Cliente clienteAtual;

    public PlataformaStreaming(String nome) {
        this.nome = nome;
        this.series = new HashSet<Serie>();
        this.filmes = new HashSet<Filme>();
        this.clientes = new HashSet<Cliente>();
        this.clienteAtual = null;
    }

    public String getNome() {
        return nome;
    }

    public HashSet<Serie> getSeries() {
        return series;
    }

    public HashSet<Cliente> getClientes() {
        return clientes;
    }

    public Cliente login (String nomeUsuario, String senha){
        for(Cliente i : clientes) {
            if(i.getNomeDeUsuario() == nomeUsuario && i.getSenha() == senha) {
                clienteAtual = i;
                return clienteAtual;
            }
        }
        return clienteAtual;
    }

    public void adicionarSerie(Serie serie){
        series.add(serie);
    }

    public void adicionarFilme(Filme filme){
        filmes.add(filme);
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

    public List<Serie> filtrarSeriesPorGenero(String genero){
    	List<Serie> result = new ArrayList<Serie>();
        for(Serie i : series) {
            if(i.getGenero() == genero) {
                result.add(i);
            }
        }

        return result;
    }

    public List<Serie> filtrarSeriesPorIdioma(String idioma){
    	List<Serie> result = new ArrayList<Serie>();
        for(Serie i : series) {
            if(i.getIdioma() == idioma) {
                result.add(i);
            }
        }

        return result;
    }

    public List<Serie> filtrarSeriesPorQtdEpisodios(int quantEpisodios){
        List<Serie> result = new ArrayList<Serie>();
        for(Serie i : series) {
            if(i.getQuantidadeEpisodios() == quantEpisodios) {
                result.add(i);
            }
        }

        return result;
    }

    public List<Serie> filtrarSeriesPorNome(String nome){
        List<Serie> result = new ArrayList<Serie>();
        for(Serie i : series) {
            if(i.getNome() == nome) {
                result.add(i);
            }
        }



        return result;
    }

    public void registrarAudiencia(Serie serie){
    	clienteAtual.registrarAudiencia(serie);
        serie.setAudiencia(1);
    }
}
