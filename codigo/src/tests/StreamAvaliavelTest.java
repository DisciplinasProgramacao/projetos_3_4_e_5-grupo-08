import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import app.Filme;
import app.Serie;
import app.Stream;
import app.StreamAvaliavel;

public class StreamAvaliavelTest {
    @Test
    public void testarStreamComDadosCompletosSerie() {
        StreamAvaliavel stream = new Serie(1, "Netflix", 2, 0, "2022-01-01", 0);
        Assertions.assertEquals(1, stream.getId());
        Assertions.assertEquals("Netflix", stream.getNome());
        Assertions.assertEquals(Stream.generos[2], stream.getGenero());
        Assertions.assertEquals(Stream.idiomas[0], stream.getIdioma());
        Assertions.assertEquals("2022-01-01", stream.getDataLancamento());
    }
    
    @Test
    public void testarStreamComDadosCompletosFilme() {
        StreamAvaliavel stream = new Filme(1, "Netflix", 2, 0, "2022-01-01", 0);
        Assertions.assertEquals(1, stream.getId());
        Assertions.assertEquals("Netflix", stream.getNome());
        Assertions.assertEquals(Stream.generos[2], stream.getGenero());
        Assertions.assertEquals(Stream.idiomas[0], stream.getIdioma());
        Assertions.assertEquals("2022-01-01", stream.getDataLancamento());
    }

    @Test
    public void testarStreamComDadosAleatoriosSerie() {
        StreamAvaliavel stream = new Serie(1, "Netflix", "2022-01-01");
        Assertions.assertEquals(1, stream.getId());
        Assertions.assertEquals("Netflix", stream.getNome());
        Assertions.assertNotNull(stream.getGenero());
        Assertions.assertNotNull(stream.getIdioma());
        Assertions.assertEquals("2022-01-01", stream.getDataLancamento());
    }
    
    @Test
    public void testarStreamComDadosAleatoriosFilme() {
        StreamAvaliavel stream = new Filme(1, "Netflix", "2022-01-01", 0);
        Assertions.assertEquals(1, stream.getId());
        Assertions.assertEquals("Netflix", stream.getNome());
        Assertions.assertNotNull(stream.getGenero());
        Assertions.assertNotNull(stream.getIdioma());
        Assertions.assertEquals("2022-01-01", stream.getDataLancamento());
    }

    @Test
    public void testarRegistrarAudienciaSerie() {
        StreamAvaliavel stream = new Serie(1, "Netflix", 2, 0, "2022-01-01", 0);
        stream.registrarAudiencia();
        stream.registrarAudiencia();
        Assertions.assertEquals(2, stream.getAudiencia());
    }
    
    @Test
    public void testarRegistrarAudienciaFilme() {
        StreamAvaliavel stream = new Filme(1, "Netflix", 2, 0, "2022-01-01", 0);
        stream.registrarAudiencia();
        stream.registrarAudiencia();
        Assertions.assertEquals(2, stream.getAudiencia());
    }

    @Test
    public void testarAvaliarSerie() {
        StreamAvaliavel stream = new Serie(1, "Netflix", 2, 0, "2022-01-01", 0);
        stream.avaliar(4);
        Assertions.assertEquals(4, stream.getAvaliacao());

        stream.avaliar(3.0);
        Assertions.assertEquals(3.75, stream.getAvaliacao());
    }
    
    @Test
    public void testarAvaliarFilme() {
        StreamAvaliavel stream = new Filme(1, "Netflix", 2, 0, "2022-01-01", 0);
        stream.avaliar(4.5);
        Assertions.assertEquals(4.5, stream.getAvaliacao());

        stream.avaliar(3.0);
        Assertions.assertEquals(3.75, stream.getAvaliacao());
    }
}
