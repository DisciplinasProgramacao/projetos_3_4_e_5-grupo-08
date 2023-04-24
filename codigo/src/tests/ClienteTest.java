package tests;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.Cliente;
import app.Serie;


public class ClienteTest {
    private Cliente cliente;
	private Serie serie1;
	private Serie serie2;
	
	@BeforeEach
	public void setUp() {
		// Configuração inicial para os testes
		cliente = new Cliente("usuario", "senha");
		serie1 = new Serie("Serie1", "Genero1", "Idioma1", 10, 0);
		serie2 = new Serie("Serie2", "Genero2", "Idioma2", 20, 0);
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
	// 	cliente.retirarDaLista("Serie1");
	// 	assertEquals(1, cliente.listaParaVer.size());
	// 	assertFalse(cliente.listaParaVer.contains(serie1));
	// 	assertTrue(cliente.listaParaVer.contains(serie2));
	// }
	
	@Test
	public void testFiltrarPorGenero() {
		cliente.registrarAudiencia(serie1);
		cliente.adicionarNaLista(serie2);
		List<Serie> listaFiltrada = cliente.filtrarPorGenero("Genero1");
		assertEquals(1, listaFiltrada.size());
		assertTrue(listaFiltrada.contains(serie1));
		assertFalse(listaFiltrada.contains(serie2));
	}
	
	@Test
	public void testFiltrarPorIdioma() {
		cliente.registrarAudiencia(serie1);
		cliente.adicionarNaLista(serie2);
		List<Serie> listaFiltrada = cliente.filtrarPorIdioma("Idioma1");
		assertEquals(1, listaFiltrada.size());
		assertTrue(listaFiltrada.contains(serie1));
		assertFalse(listaFiltrada.contains(serie2));
	}
	
	@Test
	public void testFiltrarPorQtdEpisodios() {
		cliente.registrarAudiencia(serie1);
		cliente.adicionarNaLista(serie2);
		List<Serie> listaFiltrada = cliente.filtrarPorQtdEpisodios(10);
		assertEquals(1, listaFiltrada.size());
		assertTrue(listaFiltrada.contains(serie1));
		assertFalse(listaFiltrada.contains(serie2));
	}
	
	// @Test
	// public void testRegistrarAudiencia() {
	// 	cliente.registrarAudiencia(serie1);
	// 	assertEquals(1, cliente.listaJaVistas.size());
	// 	assertTrue(cliente.listaJaVistas.contains(serie1));
	// }
}
