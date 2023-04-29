package tests;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.Cliente;
import app.PlataformaStreaming;
import app.Serie;
import app.Stream;

public class PlataformaStreamingTest {

    private PlataformaStreaming plataforma;

    @BeforeEach
    public void setUp() {
        plataforma = new PlataformaStreaming("Amaze");
    }

    @Test
    public void testLogin() {
        Cliente cliente = new Cliente("João", "1234", "login");
        plataforma.adicionarCliente(cliente);
        Cliente login = plataforma.login("login");
        Assertions.assertEquals(cliente, login);
    }

    @Test
    public void testAdicionarSerie() {
        Serie serie = new Serie(1, "Friends", "23/03/1020");
        plataforma.adicionarSerie(serie);
        Assertions.assertEquals(1, plataforma.getSeries().size());
        Assertions.assertTrue(plataforma.getSeries().contains(serie));
    }

    @Test
    public void testAdicionarCliente() {
        Cliente cliente = new Cliente("João", "1234", "login");
        plataforma.adicionarCliente(cliente);
        Assertions.assertEquals(1, plataforma.getClientes().size());
        Assertions.assertTrue(plataforma.getClientes().contains(cliente));
    }

    @Test
    public void testFiltrarPorGenero() {
        Cliente cliente = new Cliente("João", "1234", "login");
        plataforma.adicionarCliente(cliente);
        Serie serie = new Serie(1, "Friends", "23/03/1020");
        plataforma.adicionarSerie(serie);
        List<Stream> seriesFiltradas = plataforma.filtrarPorGenero("Comédia");
        Assertions.assertEquals(1, seriesFiltradas.size());
        Assertions.assertTrue(seriesFiltradas.contains(serie));
    }

    @Test
    public void testFiltrarPorIdioma() {
        Cliente cliente = new Cliente("João", "1234", "login");
        plataforma.adicionarCliente(cliente);
        Serie serie = new Serie(1, "Friends", "23/03/1020");
        plataforma.adicionarSerie(serie);
        List<Stream> seriesFiltradas = plataforma.filtrarPorIdioma("Inglês");
        Assertions.assertEquals(1, seriesFiltradas.size());
        Assertions.assertTrue(seriesFiltradas.contains(serie));
    }

    // @Test
    // public void testFiltrarPorQtdEpisodios() {
    //     Cliente cliente = new Cliente("João", "1234");
    //     plataforma.adicionarCliente(cliente);
    //     Serie serie = new Serie(1, "Friends", "23/03/1020");
    //     plataforma.adicionarSerie(serie);
    //     List<Serie> seriesFiltradas = plataforma.filtrarPorQtdEpisodios(10);
    //     Assertions.assertEquals(1, seriesFiltradas.size());
    //     Assertions.assertTrue(seriesFiltradas.contains(serie));
    // }

    @Test
    public void testRegistrarAudiencia() {
        Cliente cliente = new Cliente("João", "1234", "login");
        plataforma.adicionarCliente(cliente);
        Serie serie = new Serie(1, "Friends", "23/03/1020");
        plataforma.adicionarSerie(serie);
        plataforma.login("login");
        plataforma.registrarAudiencia(serie);
        Assertions.assertEquals(1, serie.getAudiencia());
    }
}
