import app.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SerieTest {

    @Test
    public void testarSerieComDadosCompletosSerie() {
        Serie serie = new Serie(1, "Friends", 0, 0, "1994-09-22", 236);
        Assertions.assertEquals(1, serie.getId());
        Assertions.assertEquals("Friends", serie.getNome());
        Assertions.assertEquals(Stream.generos[0], serie.getGenero());
        Assertions.assertEquals(Stream.idiomas[0], serie.getIdioma());
        Assertions.assertEquals("1994-09-22", serie.getDataLancamento());
        Assertions.assertEquals(0, serie.getAudiencia());
        Assertions.assertEquals(0.0, serie.getAvaliacao());
        Assertions.assertEquals(236, serie.getQuantidadeEpisodios());
    }

    @Test
    public void testarSerieComDadosAleatoriosSerie() {
        Serie serie = new Serie(1, "Friends", "1994-09-22");
        Assertions.assertEquals(1, serie.getId());
        Assertions.assertEquals("Friends", serie.getNome());
        Assertions.assertNotNull(serie.getGenero());
        Assertions.assertNotNull(serie.getIdioma());
        Assertions.assertEquals("1994-09-22", serie.getDataLancamento());
        Assertions.assertEquals(0, serie.getAudiencia());
        Assertions.assertEquals(0.0, serie.getAvaliacao());
        Assertions.assertEquals(0, serie.getQuantidadeEpisodios());
    }

    @Test
    public void testarSetQuantidadeEpisodiosSerie() {
        Serie serie = new Serie(1, "Friends", 0, 0, "1994-09-22", 236);
        serie.setQuantidadeEpisodios(300);
        Assertions.assertEquals(300, serie.getQuantidadeEpisodios());
    }

    @Test
    public void testarToStringSerie() {
        Serie serie = new Serie(1, "Friends", 0, 0, "1994-09-22", 236);
        String expected = "Sobre a Série: \n----------------------------------------------------------------\n" +
                "Id: 1\nNome: Friends\nGênero: Comédia\nIdioma: Inglês\nAudiência: 0\nData: 1994-09-22\n" +
                "Quantidade de Episódios: 236";
        Assertions.assertEquals(expected, serie.toString());
    }
}
