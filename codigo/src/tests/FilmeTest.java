import app.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FilmeTest {

    @Test
    public void testarFilmeComDadosCompletos() {
        Filme filme = new Filme(1, "Titanic", 6, 0, "1997-12-19", 194.5f);
        Assertions.assertEquals(1, filme.getId());
        Assertions.assertEquals("Titanic", filme.getNome());
        Assertions.assertEquals(Stream.generos[6], filme.getGenero());
        Assertions.assertEquals(Stream.idiomas[0], filme.getIdioma());
        Assertions.assertEquals("1997-12-19", filme.getDataLancamento());
        Assertions.assertEquals(0, filme.getAudiencia());
        Assertions.assertEquals(0.0, filme.getAvaliacao());
        Assertions.assertEquals(194.5f, filme.getDuracao());
    }

    @Test
    public void testarFilmeComDadosAleatorios() {
        Filme filme = new Filme(1, "Titanic", "1997-12-19", 194.5f);
        Assertions.assertEquals(1, filme.getId());
        Assertions.assertEquals("Titanic", filme.getNome());
        Assertions.assertNotNull(filme.getGenero());
        Assertions.assertNotNull(filme.getIdioma());
        Assertions.assertEquals("1997-12-19", filme.getDataLancamento());
        Assertions.assertEquals(0, filme.getAudiencia());
        Assertions.assertEquals(0.0, filme.getAvaliacao());
        Assertions.assertEquals(194.5f, filme.getDuracao());
    }

    @Test
    public void testarSetDuracao() {
        Filme filme = new Filme(1, "Titanic", 6, 0, "1997-12-19", 194.5f);
        filme.setDuracao(210.75f);
        Assertions.assertEquals(210.75f, filme.getDuracao());
    }

    @Test
    public void testarToString() {
        Filme filme = new Filme(1, "Titanic", 6, 0, "1997-12-19", 194.5f);
        String expected = "Sobre o Filme: \n----------------------------------------------------------------\n" +
                "Id: 1\nNome: Titanic\nGênero: Drama\nIdioma: Ingles\nData: 1997-12-19\nAudiência: 0\nAvaliação: 0"+ "\n\nDuração: 194.5\n\n";
        Assertions.assertEquals(expected, filme.toString());
    }
}
