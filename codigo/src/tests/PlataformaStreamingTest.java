import app.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

public class PlataformaStreamingTest {

    @Test
    public void testarPlataformaStreaming() throws StreamNaoEncontradoException {
        // Criação da plataforma de streaming
        PlataformaStreaming plataforma = new PlataformaStreaming("Streaming 1");

        // Verifica o nome da plataforma
        Assertions.assertEquals("Streaming 1", plataforma.getNome());

        // Verifica a coleção de streams vazia
        Assertions.assertTrue(plataforma.getColecao().isEmpty());

        // Verifica a lista de clientes vazia
        Assertions.assertTrue(plataforma.getClientes().isEmpty());

        // Adiciona um cliente à plataforma
        Cliente cliente1 = new Cliente("cliente1", "senha1", "Cliente 1");
        plataforma.adicionarCliente(cliente1);

        // Verifica se o cliente foi adicionado corretamente
        Assertions.assertEquals(1, plataforma.getClientes().size());
        Assertions.assertNotNull(plataforma.getClientes().get(cliente1.getLogin()));

        // Faz login do cliente na plataforma
        plataforma.loginPlataforma("cliente1", "senha1");

        // Verifica o cliente atual na plataforma
        Assertions.assertEquals(cliente1, plataforma.getClienteAtual());

        // Adiciona uma série à coleção da plataforma
        Serie serie = new Serie(1, "Friends", 0, 0, "1994-09-22", 236);
        plataforma.adicionarColecao(serie);

        // Verifica se a série foi adicionada corretamente à coleção da plataforma
        Assertions.assertEquals(1, plataforma.getColecao().size());
        Assertions.assertNotNull(plataforma.getColecao().get(serie.getId()));

        // Filtra séries por gênero
        List<Stream> resultadoGenero = plataforma.filtrarPorGenero(0);
        Assertions.assertEquals(serie, resultadoGenero);

        // Filtra séries por idioma
        List<Stream> resultadoIdioma = plataforma.filtrarPorIdioma(0);
        Assertions.assertEquals(serie, resultadoIdioma);

        // Filtra séries por nome
        Stream resultadoNome = plataforma.filtrarPorNome("Friends");
        Assertions.assertEquals(serie, resultadoNome);

        // Registra audiência de uma série pelo cliente atual
        plataforma.registrarAudiencia(serie);

        // Verifica se a audiência foi registrada corretamente na série e no cliente
        Assertions.assertEquals(1, serie.getAudiencia());

        // Encontra uma stream por ID
        Stream streamEncontrada = plataforma.encontraStreamPorId(1);
        Assertions.assertEquals(serie, streamEncontrada);
    }
}
