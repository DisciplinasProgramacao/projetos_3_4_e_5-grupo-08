package app

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ClienteTest {

    @Test
    public void testarCriarCliente() {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Assertions.assertEquals("joao123", cliente.getNomeDeUsuario());
        Assertions.assertEquals("joao@example.com", cliente.getLogin());
        Assertions.assertEquals("senha123", cliente.getSenha());
        Assertions.assertNotNull(cliente.mostrarListaJaVista());
        Assertions.assertNotNull(cliente.mostrarListaParaAssistir());
    }

    @Test
    public void testarAdicionarNaListaParaVer() {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme = new Filme(1, "Titanic", "Drama", "Inglês", "1997-12-19", 194.5f);
        cliente.adicionarNaListaParaVer(filme);
        List<Stream> listaParaVer = cliente.mostrarListaParaAssistir();
        Assertions.assertEquals(1, listaParaVer.size());
        Assertions.assertEquals(filme, listaParaVer.get(0));
    }

    @Test
    public void testarAdicionarNaListaJaVisto() {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme = new Filme(1, "Titanic", "Drama", "Inglês", "1997-12-19", 194.5f);
        cliente.adicionarNaListaJaVisto(filme);
        List<AvaliacaoStream> listaJaVistas = cliente.mostrarListaJaVista();
        Assertions.assertEquals(1, listaJaVistas.size());
        Assertions.assertEquals(filme, listaJaVistas.get(0).getStream());
    }

    @Test
    public void testarRetirarDaLista() {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme = new Filme(1, "Titanic", "Drama", "Inglês", "1997-12-19", 194.5f);
        cliente.adicionarNaListaParaVer(filme);
        cliente.retirarDaLista("Titanic");
        List<Stream> listaParaVer = cliente.mostrarListaParaAssistir();
        Assertions.assertEquals(0, listaParaVer.size());
    }

    @Test
    public void testarFiltrarPorGenero() {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme1 = new Filme(1, "Titanic", "Drama", "Inglês", "1997-12-19", 194.5f);
        Filme filme2 = new Filme(2, "Interestelar", "Ficção Científica", "Inglês", "2014-11-07", 169.0f);
        cliente.adicionarNaListaJaVisto(filme1);
        cliente.adicionarNaListaParaVer(filme2);
        List<Stream> filmesDrama = cliente.filtrarPorGenero("Drama");
        List<Stream> filmesFiccao = cliente.filtrarPorGenero("Ficção Científica");
        Assertions.assertEquals(1, filmesDrama.size());
        Assertions.assertEquals(1, filmesFiccao.size());
        Assertions.assertEquals(filme1, filmesDrama.get(0));
        Assertions.assertEquals(filme2, filmesFiccao.get(0));
    }

    @Test
    public void testarFiltrarPorIdioma() {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme1 = new Filme(1, "Titanic", "Drama", "Inglês", "1997-12-19", 194.5f);
        Filme filme2 = new Filme(2, "Interestelar", "Ficção Científica", "Inglês", "2014-11-07", 169.0f);
        cliente.adicionarNaListaJaVisto(filme1);
        cliente.adicionarNaListaParaVer(filme2);
        List<Stream> filmesIngles = cliente.filtrarPorIdioma("Inglês");
        Assertions.assertEquals(2, filmesIngles.size());
        Assertions.assertEquals(filme1, filmesIngles.get(0));
        Assertions.assertEquals(filme2, filmesIngles.get(1));
    }

    @Test
    public void testarAvaliar() {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme = new Filme(1, "Titanic", "Drama", "Inglês", "1997-12-19", 194.5f);
        cliente.adicionarNaListaJaVisto(filme);
        cliente.avaliar(1, 4.5f);
        List<AvaliacaoStream> listaJaVistas = cliente.mostrarListaJaVista();
        Assertions.assertEquals(1, listaJaVistas.size());
        Assertions.assertEquals(4.5f, listaJaVistas.get(0).getAvaliacao());
    }

    @Test
    public void testarRegistrarAudiencia() {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme = new Filme(1, "Titanic", "Drama", "Inglês", "1997-12-19", 194.5f);
        cliente.registrarAudiencia(filme);
        List<AvaliacaoStream> listaJaVistas = cliente.mostrarListaJaVista();
        Assertions.assertEquals(1, listaJaVistas.size());
        Assertions.assertEquals(filme, listaJaVistas.get(0).getStream());
    }

    @Test
    public void testarAvaliados() {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme1 = new Filme(1, "Titanic", "Drama", "Inglês", "1997-12-19", 194.5f);
        Filme filme2 = new Filme(2, "Interestelar", "Ficção Científica", "Inglês", "2014-11-07", 169.0f);
        cliente.adicionarNaListaJaVisto(filme1);
        cliente.adicionarNaListaParaVer(filme2);
        cliente.avaliar(1, 4.5f);
        List<Stream> filmesAvaliados = cliente.avaliados();
        Assertions.assertEquals(1, filmesAvaliados.size());
        Assertions.assertEquals(filme1, filmesAvaliados.get(0));
    }
}
