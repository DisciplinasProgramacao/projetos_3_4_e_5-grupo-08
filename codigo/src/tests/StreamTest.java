package app

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StreamTest {

    @Test
    public void testarStreamComDadosCompletosSerie() {
        Stream stream = new Serie(1, "Netflix", "Ação", "Inglês", "2022-01-01", 0);
        Assertions.assertEquals(1, stream.getId());
        Assertions.assertEquals("Netflix", stream.getNome());
        Assertions.assertEquals("Ação", stream.getGenero());
        Assertions.assertEquals("Inglês", stream.getIdioma());
        Assertions.assertEquals("2022-01-01", stream.getDataLancamento());
        Assertions.assertEquals(0, stream.getAudiencia());
        Assertions.assertEquals(0.0, stream.getAvaliacao());
    }
    
    @Test
    public void testarStreamComDadosCompletosFilme() {
        Stream stream = new Filme(1, "Netflix", "Ação", "Inglês", "2022-01-01", 0);
        Assertions.assertEquals(1, stream.getId());
        Assertions.assertEquals("Netflix", stream.getNome());
        Assertions.assertEquals("Ação", stream.getGenero());
        Assertions.assertEquals("Inglês", stream.getIdioma());
        Assertions.assertEquals("2022-01-01", stream.getDataLancamento());
        Assertions.assertEquals(0, stream.getAudiencia());
        Assertions.assertEquals(0.0, stream.getAvaliacao());
    }

    @Test
    public void testarStreamComDadosAleatoriosSerie() {
        Stream stream = new Serie(1, "Netflix", "2022-01-01");
        Assertions.assertEquals(1, stream.getId());
        Assertions.assertEquals("Netflix", stream.getNome());
        Assertions.assertNotNull(stream.getGenero());
        Assertions.assertNotNull(stream.getIdioma());
        Assertions.assertEquals("2022-01-01", stream.getDataLancamento());
        Assertions.assertEquals(0, stream.getAudiencia());
        Assertions.assertEquals(0.0, stream.getAvaliacao());
    }
    
    @Test
    public void testarStreamComDadosAleatoriosFilme() {
        Stream stream = new Filme(1, "Netflix", "2022-01-01", 0);
        Assertions.assertEquals(1, stream.getId());
        Assertions.assertEquals("Netflix", stream.getNome());
        Assertions.assertNotNull(stream.getGenero());
        Assertions.assertNotNull(stream.getIdioma());
        Assertions.assertEquals("2022-01-01", stream.getDataLancamento());
        Assertions.assertEquals(0, stream.getAudiencia());
        Assertions.assertEquals(0.0, stream.getAvaliacao());
    }

    @Test
    public void testarRegistrarAudienciaSerie() {
        Stream stream = new Serie(1, "Netflix", "Ação", "Inglês", "2022-01-01", 0);
        stream.registrarAudiencia();
        stream.registrarAudiencia();
        Assertions.assertEquals(2, stream.getAudiencia());
    }
    
    @Test
    public void testarRegistrarAudienciaFilme() {
        Stream stream = new Filme(1, "Netflix", "Ação", "Inglês", "2022-01-01", 0);
        stream.registrarAudiencia();
        stream.registrarAudiencia();
        Assertions.assertEquals(2, stream.getAudiencia());
    }

    @Test
    public void testarAvaliarSerie() {
        Stream stream = new Serie(1, "Netflix", "Ação", "Inglês", "2022-01-01", 0);
        stream.avaliar(4);
        Assertions.assertEquals(4, stream.getAvaliacao());

        stream.avaliar(3.0);
        Assertions.assertEquals(3.75, stream.getAvaliacao());
    }
    
    @Test
    public void testarAvaliarFilme() {
        Stream stream = new Filme(1, "Netflix", "Ação", "Inglês", "2022-01-01", 0);
        stream.avaliar(4.5);
        Assertions.assertEquals(4.5, stream.getAvaliacao());

        stream.avaliar(3.0);
        Assertions.assertEquals(3.75, stream.getAvaliacao());
    }

    @Test
    public void testarToStringSerie() {
        Stream stream = new Serie(1, "Netflix", "Ação", "Inglês", "2022-01-01", 0);
        String expected = "Id: 1\nNome: Netflix\nGênero: Ação\nIdioma: Inglês\nAudiência: 0\nData: 2022-01-01\n";
        Assertions.assertEquals(expected, stream.toString());
    }
    
    @Test
    public void testarToStringFilme() {
        Stream stream = new Filme(1, "Netflix", "Ação", "Inglês", "2022-01-01", 0);
        String expected = "Id: 1\nNome: Netflix\nGênero: Ação\nIdioma: Inglês\nAudiência: 0\nData: 2022-01-01\n";
        Assertions.assertEquals(expected, stream.toString());
    }
}
