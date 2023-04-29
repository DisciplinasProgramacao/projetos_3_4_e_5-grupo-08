package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.Filme;

public class FIlmeTest {
    private Filme filme;
    private final int ID_FILME = 1;
    private final String NOME_FILME = "O Senhor dos Anéis";
    private final String DATA_LANCAMENTO = "19-12-2001";

    @BeforeEach
    public void setUp() throws ParseException {
        this.filme = new Filme(ID_FILME, NOME_FILME, DATA_LANCAMENTO);
    }

    @Test
    public void testGetIdFilme() {
        assertEquals(ID_FILME, filme.getId());
    }

    @Test
    public void testGetNome() {
        assertEquals(NOME_FILME, filme.getNome());
    }

    @Test
    public void testSetNome() {
        String novoNome = "O Hobbit";
        filme.setNome(novoNome);
        assertEquals(novoNome, filme.getNome());
    }

    @Test
    public void testGetDataDeLancamento() {
        assertEquals(DATA_LANCAMENTO, filme.getDataLancamento());
    }

    @Test
    public void testSetDataDeLancamento() {
        String novaData = "14-12-2012";
        filme.setDataLancamento(novaData);
        assertEquals(novaData, filme.getDataLancamento());
    }

    @Test
    public void testGetDuracao() {
        assertEquals(0.0, filme.getDuracao(), 0.0);
    }

    @Test
    public void testSetDuracao() {
        float novaDuracao = 120.0f;
        filme.setDuracao(novaDuracao);
        assertEquals(novaDuracao, filme.getDuracao(), 0.0);
    }

}
