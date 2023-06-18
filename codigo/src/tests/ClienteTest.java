import app.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ClienteTest {

    @Test
    public void testarCriarCliente() throws ListaVaziaException {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Assertions.assertEquals("joao123", cliente.getNomeDeUsuario());
        Assertions.assertEquals("joao@example.com", cliente.getLogin());
        Assertions.assertEquals("senha123", cliente.getSenha());
        Assertions.assertNull(cliente.mostrarListaJaVista());
        Assertions.assertNull(cliente.mostrarListaParaAssistir());
    }

    @Test
    public void testarAdicionarNaListaParaVer() throws ListaVaziaException, PeliculaJaExistenteException {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme = new Filme(1, "Titanic", 6, 0, "1997-12-19", 194.5f);
        cliente.adicionarNaListaParaVer(filme);
        List<StreamAvaliavel> listaParaVer = cliente.mostrarListaParaAssistir();
        Assertions.assertEquals(1, listaParaVer.size());
        Assertions.assertEquals(filme, listaParaVer.get(0));
    }

    @Test
    public void testarAdicionarNaListaJaVisto() throws ListaVaziaException, PeliculaJaExistenteException {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme = new Filme(1, "Titanic", 6, 0, "1997-12-19", 194.5f);
        cliente.adicionarNaListaJaVisto(filme);
        List<StreamAvaliavel> listaJaVistas = cliente.mostrarListaJaVista();
        Assertions.assertEquals(1, listaJaVistas.size());
        Assertions.assertEquals(filme, listaJaVistas.get(0));
    }

    @Test
    public void testarRetirarDaLista() throws ListaVaziaException, PeliculaJaExistenteException {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme = new Filme(1, "Titanic", 6, 0, "1997-12-19", 194.5f);
        cliente.adicionarNaListaParaVer(filme);
        cliente.retirarDaLista("Titanic");
        List<StreamAvaliavel> listaParaVer = cliente.mostrarListaParaAssistir();
        Assertions.assertEquals(0, listaParaVer.size());
    }

    @Test
    public void testarFiltrarPorGenero() throws ListaVaziaException, PeliculaJaExistenteException {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme1 = new Filme(1, "Titanic", 6, 0, "1997-12-19", 194.5f);
        Filme filme2 = new Filme(2, "Interestelar", 3, 0, "2014-11-07", 169.0f);
        cliente.adicionarNaListaJaVisto(filme1);
        cliente.adicionarNaListaParaVer(filme2);
        List<Stream> filmesDrama = cliente.filtrarPorGenero(Stream.generos[6]);
        List<Stream> filmesFiccao = cliente.filtrarPorGenero(Stream.generos[3]);
        Assertions.assertEquals(1, filmesDrama.size());
        Assertions.assertEquals(1, filmesFiccao.size());
        Assertions.assertEquals(filme1, filmesDrama.get(0));
        Assertions.assertEquals(filme2, filmesFiccao.get(0));
    }

    @Test
    public void testarFiltrarPorIdioma() throws ListaVaziaException, PeliculaJaExistenteException {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme1 = new Filme(1, "Titanic", 6, 0, "1997-12-19", 194.5f);
        Filme filme2 = new Filme(2, "Interestelar", 3, 0, "2014-11-07", 169.0f);
        cliente.adicionarNaListaJaVisto(filme1);
        cliente.adicionarNaListaParaVer(filme2);
        List<Stream> filmesIngles = cliente.filtrarPorIdioma(Stream.idiomas[0]);
        Assertions.assertEquals(2, filmesIngles.size());
        Assertions.assertEquals(filme1, filmesIngles.get(0));
        Assertions.assertEquals(filme2, filmesIngles.get(1));
    }

    @Test
    public void testarAvaliar() throws ListaVaziaException, PeliculaJaExistenteException, PeliculaJaAvaliadaException {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme = new Filme(1, "Titanic", 6, 0, "1997-12-19", 194.5f);
        cliente.adicionarNaListaJaVisto(filme);
        cliente.avaliar(1, 4.5f);
        List<StreamAvaliavel> listaJaVistas = cliente.mostrarListaJaVista();
        Assertions.assertEquals(1, listaJaVistas.size());
        Assertions.assertEquals(4.5f, listaJaVistas.get(0).getAvaliacao());
    }

    @Test
    public void testarRegistrarAudiencia() throws ListaVaziaException, PeliculaJaExistenteException {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme = new Filme(1, "Titanic", 6, 0, "1997-12-19", 194.5f);
        cliente.registrarAudiencia(filme);
        List<StreamAvaliavel> listaJaVistas = cliente.mostrarListaJaVista();
        Assertions.assertEquals(1, listaJaVistas.size());
        Assertions.assertEquals(filme, listaJaVistas.get(0));
    }

    @Test
    public void testarAvaliados() throws ListaVaziaException, PeliculaJaExistenteException, PeliculaJaAvaliadaException {
        Cliente cliente = new Cliente("joao123", "joao@example.com", "senha123");
        Filme filme1 = new Filme(1, "Titanic", 6, 0, "1997-12-19", 194.5f);
        Filme filme2 = new Filme(2, "Interestelar", 3, 0, "2014-11-07", 169.0f);
        cliente.adicionarNaListaJaVisto(filme1);
        cliente.adicionarNaListaParaVer(filme2);
        cliente.avaliar(1, 4.5f);
        List<Stream> filmesAvaliados = cliente.avaliados();
        Assertions.assertEquals(1, filmesAvaliados.size());
        Assertions.assertEquals(filme1, filmesAvaliados.get(0));
    }
}
