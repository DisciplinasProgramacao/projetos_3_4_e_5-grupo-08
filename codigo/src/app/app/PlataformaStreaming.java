package app;

public class PlataformaStreaming {

    private String nome;
    private Hash<Serie> series;
    private Hash<Cliente> clientes;
    private Cliente clienteAtual;

    public Cliente login (String nomeUsuario, String senha){
        return ;
    }

    public void adicionarSerie(Serie serie){

    }

    public void adicionarCliente(Cliente cliente){
    	
    }

    public Lista<Serie> filtrarPorGenero(String genero){
        
    	clienteAtual.filtrarPorGenero(genero);
    	return ;
    }

    public Lista<Serie> filtrarPorIdioma(String idioma){
        clienteAtual.filtrarPorIdioma(idioma);
    	return ;
    }

    public Lista<Serie> filtrarPorQtdEpisodios(int quantEpisodios){
        clienteAtual.filtrarPorQtdEpisodios(quantEpisodios);
    	return ;
    }

    public void registrarAudiencia(Serie serie){
    	clienteAtual.registrarAudiencia(serie);
    }
}
