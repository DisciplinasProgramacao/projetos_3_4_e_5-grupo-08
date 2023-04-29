package tests;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.Cliente;
import app.Serie;
import app.Stream;


public class ClienteTest {
    private Cliente cliente;
	private Serie serie1;
	private Serie serie2;
	
	@BeforeEach
	public void setUp() {
		// Configuração inicial para os testes
		cliente = new Cliente("usuario", "senha", "login");
		serie1 = new Serie(1,"Stream1", "22-01-2020");
		serie2 = new Serie(2,"Stream2", "22-01-2020");
	}
	
	// @Test
	// public void testAdicionarNaLista() {
	// 	cliente.adicionarNaLista(serie1);
	// 	assertEquals(1, cliente.listaParaVer.size());
	// 	assertEquals(cliente.listaParaVer.contains(serie1));
	// }
	
	// @Test
	// public void testRetirarDaLista() {
	// 	cliente.adicionarNaLista(serie1);
	// 	cliente.adicionarNaLista(serie2);
	// 	assertEquals(2, cliente.listaParaVer.size());
	// 	cliente.retirarDaLista("Stream1");
	// 	assertEquals(1, cliente.listaParaVer.size());
	// 	assertFalse(cliente.listaParaVer.contains(serie1));
	// 	assertTrue(cliente.listaParaVer.contains(serie2));
	// }
	
	@Test
	public void testFiltrarPorGenero() {
		cliente.registrarAudiencia(serie1);
		cliente.adicionarNaListaParaVer(serie2);
		List<Stream> listaFiltrada = cliente.filtrarPorGenero("Genero1");
		assertEquals(1, listaFiltrada.size());
		assertTrue(listaFiltrada.contains(serie1));
		assertFalse(listaFiltrada.contains(serie2));
	}
	
	@Test
	public void testFiltrarPorIdioma() {
		cliente.registrarAudiencia(serie1);
		cliente.adicionarNaListaParaVer(serie2);
		List<Stream> listaFiltrada = cliente.filtrarPorIdioma("Idioma1");
		assertEquals(1, listaFiltrada.size());
		assertTrue(listaFiltrada.contains(serie1));
		assertFalse(listaFiltrada.contains(serie2));
	}
	
	// @Test
	// public void testFiltrarPorQtdEpisodios() {
	// 	cliente.registrarAudiencia(serie1);
	// 	cliente.adicionarNaLista(serie2);
	// 	List<Stream> listaFiltrada = cliente.filtrarPorQtdEpisodios(10);
	// 	assertEquals(1, listaFiltrada.size());
	// 	assertTrue(listaFiltrada.contains(serie1));
	// 	assertFalse(listaFiltrada.contains(serie2));
	// }
	
	// @Test
	// public void testRegistrarAudiencia() {
	// 	cliente.registrarAudiencia(serie1);
	// 	assertEquals(1, cliente.listaJaVistas.size());
	// 	assertTrue(cliente.listaJaVistas.contains(serie1));
	// }
}
