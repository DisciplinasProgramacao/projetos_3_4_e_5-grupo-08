//CLASSE STREAMAVALIAVEL E APLICACAO MODIFICADA!!!

package app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe para gerar relatórios de uma plataforma de streaming.
 */
public class Relatorio {
    private PlataformaStreaming plataforma;
    private String clienteComMaisMidias;
    private int maiorNumeroDeMidias;
    private String clienteComMaisAvaliacoes;
    private int maiorNumeroDeAvaliacoes;
    private double porcentagemClientesComPeloMenos15Avaliacoes;
    private List<StreamAvaliavel> top10Midias;
    private List<StreamAvaliavel> top10MidiasComMaisVisualizacoes;

    /**
     * Construtor para a classe Relatorio.
     *
     * @param plataforma
     */
    public Relatorio(PlataformaStreaming plataforma) {
        this.plataforma = plataforma;
        this.clienteComMaisMidias = "";
        this.maiorNumeroDeMidias = 0;
        this.clienteComMaisAvaliacoes = "";
        this.maiorNumeroDeAvaliacoes = 0;
        this.plataforma = plataforma;
        this.porcentagemClientesComPeloMenos15Avaliacoes = 0.0;
        this.top10Midias = new ArrayList<>();
        this.top10MidiasComMaisVisualizacoes = new ArrayList<>();
    }

    /**
     * Retorna o nome do cliente com mais mídias.
     *
     * @return o nome do cliente com mais mídias
     */
    public String getClienteComMaisMidias() {
        return clienteComMaisMidias;
    }

    /**
     * Retorna o maior número de mídias.
     *
     * @return o maior número de mídias
     */
    public int getMaiorNumeroDeMidias() {
        return maiorNumeroDeMidias;
    }

    /**
     * Retorna o nome do cliente com mais avaliações.
     *
     * @return o nome do cliente com mais avaliações
     */
    public String getClienteComMaisAvaliacoes() {
        return clienteComMaisAvaliacoes;
    }

    /**
     * Retorna o maior número de avaliações.
     *
     * @return o maior número de avaliações
     */
    public int getMaiorNumeroDeAvaliacoes() {
        return maiorNumeroDeAvaliacoes;
    }

    /**
     * Retorna a porcentagem de clientes com pelo menos 15 avaliações.
     *
     * @return a porcentagem de clientes com pelo menos 15 avaliações
     */
    public double getPorcentagemClientesComPeloMenos15Avaliacoes() {
        return porcentagemClientesComPeloMenos15Avaliacoes;
    }

    /**
     * Retorna a lista das top 10 mídias.
     *
     * @return a lista das top 10 mídias
     */
    public List<StreamAvaliavel> getTop10Midias() {
        return top10Midias;
    }

    /**
     * Retorna a lista das top 10 mídias com mais visualizações.
     *
     * @return a lista das top 10 mídias com mais visualizações
     */
    public List<StreamAvaliavel> getTop10MidiasComMaisVisualizacoes() {
        return top10MidiasComMaisVisualizacoes;
    }

    /**
     * Gera o relatório do cliente que assistiu mais mídias e quantas mídas foram
     * assistidass.
     */
    public void gerarRelatorioClienteMaisMidias() {
        HashMap<String, Cliente> clientes = plataforma.getClientes();

        for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
            Cliente cliente = entry.getValue();
            int numeroDeMidias = cliente.listaJaVistas.size();
            if (numeroDeMidias > maiorNumeroDeMidias) {
                maiorNumeroDeMidias = numeroDeMidias;
                clienteComMaisMidias = cliente.getNomeDeUsuario();
            }
        }
    }

    /**
     * Gera o relatório do cliente que tem mais avaliações, e quantas avaliações.
     */
    public void gerarRelatorioClienteMaisAvaliacoes() {
        HashMap<String, Cliente> clientes = plataforma.getClientes();

        for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
            Cliente cliente = entry.getValue();
            int numeroDeAvaliacoes = 0;
            for (AvaliacaoStream avaliacao : cliente.listaJaVistas) {
                if (avaliacao.isAvaliado()) {
                    numeroDeAvaliacoes++;
                }
            }
            if (numeroDeAvaliacoes > maiorNumeroDeAvaliacoes) {
                maiorNumeroDeAvaliacoes = numeroDeAvaliacoes;
                clienteComMaisAvaliacoes = cliente.getNomeDeUsuario();
            }
        }
    }

    /**
     * Gera o relatório da porcentagem de clientes com pelo menos 15 avaliações.
     */
    public void gerarRelatorioPorcentagemClientesComPeloMenos15Avaliacoes() {

        HashMap<String, Cliente> clientes = plataforma.getClientes();
        int totalClientes = clientes.size();
        int totalClientesComPeloMenos15Avaliacoes = 0;

        for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
            Cliente cliente = entry.getValue();
            int numeroDeAvaliacoes = 0;
            for (AvaliacaoStream avaliacao : cliente.listaJaVistas) {
                if (avaliacao.isAvaliado()) {
                    numeroDeAvaliacoes++;
                }
            }
            if (numeroDeAvaliacoes >= 15) {
                totalClientesComPeloMenos15Avaliacoes++;
            }
        }
    }

    /**
     * Gera o relatório das top 10 mídias com pelo menos 100 avaliações, em ordem
     * decrescente.
     */
    public void gerarRelatorioTop10Midias() {
        HashMap<Integer, Stream> colecao = plataforma.getColecao();
        List<StreamAvaliavel> midiasComPeloMenos100Avaliacoes = new ArrayList<>();

        for (Map.Entry<Integer, Stream> entry : colecao.entrySet()) {
            Stream stream = entry.getValue();
            if (stream instanceof StreamAvaliavel) {
                StreamAvaliavel streamAvaliavel = (StreamAvaliavel) stream;
                if (streamAvaliavel.getNumeroDeAvaliacoes() >= 100) {
                    midiasComPeloMenos100Avaliacoes.add(streamAvaliavel);
                }
            }
        }

        midiasComPeloMenos100Avaliacoes.sort(new Comparator<StreamAvaliavel>() {
            @Override
            public int compare(StreamAvaliavel o1, StreamAvaliavel o2) {
                return Double.compare(o2.getMediaDeAvaliacoes(), o1.getMediaDeAvaliacoes());
            }
        });

        top10Midias = midiasComPeloMenos100Avaliacoes.subList(0, Math.min(10, midiasComPeloMenos100Avaliacoes.size()));
    }

    /**
     * Gera o relatório das top 10 mídias com mais visualizações, em ordem
     * decrescente.
     */
    public void gerarRelatorioTop10MidiasComMaisVisualizacoes() {
        HashMap<Integer, Stream> colecao = plataforma.getColecao();
        List<StreamAvaliavel> midias = new ArrayList<>();

        for (Map.Entry<Integer, Stream> entry : colecao.entrySet()) {
            Stream stream = entry.getValue();
            if (stream instanceof StreamAvaliavel) {
                StreamAvaliavel streamAvaliavel = (StreamAvaliavel) stream;
                midias.add(streamAvaliavel);
            }
        }

        midias.sort(new Comparator<StreamAvaliavel>() {
            @Override
            public int compare(StreamAvaliavel o1, StreamAvaliavel o2) {
                return Integer.compare(o2.getNumeroDeVisualizacoes(), o1.getNumeroDeVisualizacoes());
            }
        });

        top10MidiasComMaisVisualizacoes = midias.subList(0, Math.min(10, midias.size()));
    }

    /**
     * Gera o relatório das top 10 mídias com mais visualizações, separadas por
     * gênero, com pelo menos 100 visualizações.
     */

    public Map<String, List<StreamAvaliavel>> gerarRelatorioTop10MidiasPorGenero() {
        HashMap<Integer, Stream> colecao = plataforma.getColecao();
        Map<String, List<StreamAvaliavel>> midiasPorGenero = new HashMap<>();
        Map<String, List<StreamAvaliavel>> top10MidiasPorGenero = new HashMap<>();

        for (Map.Entry<Integer, Stream> entry : colecao.entrySet()) {
            Stream stream = entry.getValue();
            if (stream instanceof StreamAvaliavel) {
                StreamAvaliavel streamAvaliavel = (StreamAvaliavel) stream;
                if (streamAvaliavel.getNumeroDeAvaliacoes() >= 100) {
                    String genero = streamAvaliavel.getGenero();
                    if (!midiasPorGenero.containsKey(genero)) {
                        midiasPorGenero.put(genero, new ArrayList<>());
                    }
                    midiasPorGenero.get(genero).add(streamAvaliavel);
                }
            }
        }

        for (Map.Entry<String, List<StreamAvaliavel>> entry : midiasPorGenero.entrySet()) {
            String genero = entry.getKey();
            List<StreamAvaliavel> midiasDoGenero = entry.getValue();
            midiasDoGenero.sort(new Comparator<StreamAvaliavel>() {
                @Override
                public int compare(StreamAvaliavel o1, StreamAvaliavel o2) {
                    return Double.compare(o2.getMediaDeAvaliacoes(), o1.getMediaDeAvaliacoes());
                }
            });
            top10MidiasPorGenero.put(genero, midiasDoGenero.subList(0, Math.min(10, midiasDoGenero.size())));
        }

        return top10MidiasPorGenero;
    }

    /**
     * Gera o relatório das top 10 mídias com mais visualizações,
     * separadas por gênero em ordem decrescente.
     * 
     */

    public Map<String, List<StreamAvaliavel>> gerarRelatorioTop10MidiasComMaisVisualizacoesPorGenero() {
        HashMap<Integer, Stream> colecao = plataforma.getColecao();
        Map<String, List<StreamAvaliavel>> midiasPorGenero = new HashMap<>();
        Map<String, List<StreamAvaliavel>> top10MidiasPorGenero = new HashMap<>();

        for (Map.Entry<Integer, Stream> entry : colecao.entrySet()) {
            Stream stream = entry.getValue();
            if (stream instanceof StreamAvaliavel) {
                StreamAvaliavel streamAvaliavel = (StreamAvaliavel) stream;
                String genero = streamAvaliavel.getGenero();
                if (!midiasPorGenero.containsKey(genero)) {
                    midiasPorGenero.put(genero, new ArrayList<>());
                }
                midiasPorGenero.get(genero).add(streamAvaliavel);
            }
        }

        for (Map.Entry<String, List<StreamAvaliavel>> entry : midiasPorGenero.entrySet()) {
            String genero = entry.getKey();
            List<StreamAvaliavel> midiasDoGenero = entry.getValue();
            midiasDoGenero.sort(new Comparator<StreamAvaliavel>() {
                @Override
                public int compare(StreamAvaliavel o1, StreamAvaliavel o2) {
                    return Integer.compare(o2.getNumeroDeVisualizacoes(), o1.getNumeroDeVisualizacoes());
                }
            });
            top10MidiasPorGenero.put(genero, midiasDoGenero.subList(0, Math.min(10, midiasDoGenero.size())));
        }

        return top10MidiasPorGenero;
    }

}
