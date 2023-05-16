import app.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AvaliacaoStreamTest {

    @Test
    public void testarConstrutor() {
        Filme filme = new Filme(1, "Titanic", "Drama", "Inglês", "1997-12-19", 194.5f);
        AvaliacaoStream avaliacao = new AvaliacaoStream(filme);
        Assertions.assertEquals(filme, avaliacao.getStream());
        Assertions.assertEquals(-1, avaliacao.getAvaliacao());
    }

    @Test
    public void testarSetAvaliacao() {
        Filme filme = new Filme(1, "Titanic", "Drama", "Inglês", "1997-12-19", 194.5f);
        AvaliacaoStream avaliacao = new AvaliacaoStream(filme);
        avaliacao.setAvaliacao(4);
        Assertions.assertEquals(4.5, avaliacao.getAvaliacao());
        Assertions.assertTrue(avaliacao.isAvaliado());
    }

    @Test
    public void testarGetIdStream() {
        Filme filme = new Filme(1, "Titanic", "Drama", "Inglês", "1997-12-19", 194.5f);
        AvaliacaoStream avaliacao = new AvaliacaoStream(filme);
        Assertions.assertEquals(1, avaliacao.getIdStream());
    }

    @Test
    public void testarIsAvaliado() {
        Filme filme = new Filme(1, "Titanic", "Drama", "Inglês", "1997-12-19", 194.5f);
        AvaliacaoStream avaliacao = new AvaliacaoStream(filme);
        Assertions.assertFalse(avaliacao.isAvaliado());
        avaliacao.setAvaliacao(4);
        Assertions.assertTrue(avaliacao.isAvaliado());
    }
}
