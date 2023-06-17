package app;

import java.util.List;
import java.util.Map;

/**
 * Projetos 3, 4 e 5 (PARTE 4)
 * 
 * Grupo 08 do laboratório da disciplina LPM 1/2023 PUC Minas - Praça da
 * Liberdade
 * 
 * @author Bernardo Cavanellas Biondini
 * @author João Vitor Bessa Lacerda
 * @author Nathan Gonçalves de Oliveira
 * 
 *         Professor: João Caram Santos de Oliveira
 */
public class Aplicacao {

    /**
     * Método para carregar séries à plataforma.
     * 
     * @param plataforma
     */
    public static void carregarDadosS(PlataformaStreaming plataforma) {
        ArquivoTextoLeitura file = new ArquivoTextoLeitura("codigo/src/POO_Series.csv");

        String dadosS = file.ler();
        dadosS = file.ler(); // pula linha

        String[] dadosSeparadosS;
        // ID;Nome;Data

        do {
            dadosSeparadosS = dadosS.split(";");
            StreamAvaliavel novaSerie = new Serie(Integer.parseInt(dadosSeparadosS[0]), dadosSeparadosS[1],
                    dadosSeparadosS[2]);
            plataforma.adicionarColecao(novaSerie);
            dadosS = file.ler();

        } while (dadosS != null);

        file.fecharArquivo();
    }

    /**
     * Método para carregar filmes à plataforma.
     * 
     * @param plataforma
     */
    public static void carregarDadosF(PlataformaStreaming plataforma) {
        ArquivoTextoLeitura file = new ArquivoTextoLeitura("codigo/src/POO_Filmes.csv");

        String dadosF = file.ler();
        dadosF = file.ler(); // pula linha

        String[] dadosSeparadosF;
        // IdFilme;Nome;DataDeLançamento;Duração(min)

        do {
            dadosSeparadosF = dadosF.split(";");
            StreamAvaliavel novoFilme = new Filme(Integer.parseInt(dadosSeparadosF[0]), dadosSeparadosF[1],
                    dadosSeparadosF[2],
                    Float.parseFloat(dadosSeparadosF[3]));
            plataforma.adicionarColecao(novoFilme);
            dadosF = file.ler();

        } while (dadosF != null);

        file.fecharArquivo();
    }

    /**
     * Método para carregar dados de audiência de série a clientes cadastrados na
     * plataforma.
     * 
     * @param plataforma
     */
    public static void carregarDadosA(PlataformaStreaming plataforma) {
        ArquivoTextoLeitura file = new ArquivoTextoLeitura("codigo/src/POO_Audiencia.csv");

        int cont = 0;

        String dadosA = file.ler();
        dadosA = file.ler(); // pula linha

        String[] dadosSeparadosA;
        // Login;F/A;IdSerie

        do {
            if (dadosA == null)
                break;
            dadosSeparadosA = dadosA.split(";");

            plataforma.login(dadosSeparadosA[0]);
            Stream stream = plataforma.encontraStreamPorId(Integer.parseInt(dadosSeparadosA[2]));
            if (stream instanceof StreamAvaliavel) {
                if (plataforma.getClienteAtual() != null && stream != null) {
                    if (dadosSeparadosA[1].equals("F")) {
                        try {
                            plataforma.adicionarNaListaParaVer((StreamAvaliavel) stream);
                        } catch (PeliculaJaExistenteException e) {
                            // TODO Auto-generated catch block
                            // e.printStackTrace();
                            cont++;
                        }
                    } else /* dadosSeparadosA[1] == "A" */ {
                        plataforma.registrarAudiencia((StreamAvaliavel) stream);
                    }
                }
            }

            dadosA = file.ler();
        } while (dadosA != null);

        file.fecharArquivo();

        System.out.println(cont);
    }

    /**
     * Método para criar perfis de cliente na plataforma.
     * 
     * @param plataforma
     */
    public static void carregarDadosE(PlataformaStreaming plataforma) {
        ArquivoTextoLeitura file = new ArquivoTextoLeitura("codigo/src/POO_Espectadores.csv");

        String dadosE = file.ler();
        dadosE = file.ler(); // pula linha

        String[] dadosSeparadosE;

        do {
            // Nome;Login;Senha
            dadosSeparadosE = dadosE.split(";");
            Cliente novoCliente = new Cliente(dadosSeparadosE[0], dadosSeparadosE[1], dadosSeparadosE[2]);
            plataforma.adicionarCliente(novoCliente);
            dadosE = file.ler();

        } while (dadosE != null);

        file.fecharArquivo();
    }

    /*
     * Método para carregar trailers à plataforma.
     * 
     * @param
     */
    public static void carregarDadosT() {

    }

    public static void main(String[] args) {

        // PLATAFORMA
        // Criando plataforma de streaming "Amaze"
        PlataformaStreaming Amaze = new PlataformaStreaming("Amaze");

        // ESPECTADORES
        // Carregando dados do arquivo "POO_Espectadores.csv" para a coleção
        carregarDadosE(Amaze);

        // SERIE
        // Carregando dados do arquivo "POO_Series.csv" para o vetor de colecao
        carregarDadosS(Amaze);

        // AUDIENCIA (SERIES)
        // Carregando dados do arquivo "POO_Audiencia.csv"
        carregarDadosA(Amaze);

        // FILME
        // Carregando dados do arquivo "POO_Filmes.csv" para a coleção
        carregarDadosF(Amaze);

        // TRAILERS
        // Carregando dados do arquivo "POO_Trailers.csv" para a coleção
        carregarDadosT();

        // REALIZAR LOGIN
        boolean acesso = false;
        while (acesso != true) {
            System.out.println("=-Realizar Login-=");
            System.out.print("Login>> ");
            String login = MyIO.readString(); // "Sha176581"
            System.out.print("Senha>> ");
            String senha = MyIO.readString(); // "SOrg05341"
            acesso = (Amaze.loginPlataforma(login, senha) == true ? true : false);
        }

        // MENU
        int op;
        do {
            System.out.printf("=-=-=-" + Amaze.getNome() + "-=-=-=\n");
            System.out.printf("Olá " + Amaze.getNomeClienteAtual() + "!\n");
            System.out.println("=-=-=-=-=-=-=-=-=");
            System.out.println("Digite uma das opções abaixo:");
            System.out.println("[1]Catálogo"); // Pesquisar series e filmes -> Aicionar "para assistir" ou "já
                                               // assistido"
            System.out.println("[2]Perfil"); // Mostrar listas de series & filmes "já assistidas" ou "para assistir" e
                                             // avaliar películas "já assistidas"
            System.out.println("[3]Adicionar série ou filme ao catálogo");
            System.out.println("[4]Entrar em outra conta");
            System.out.println("[5]Cadastrar cliente");
            System.out.println("[6]Relatórios");
            System.out.println("[0]Finalizar programa");
            System.out.println("=-=-=-=-=-=-=-=-=");
            System.out.print(">> ");
            op = MyIO.readInt();

            switch (op) {
                case 1:
                    System.out.println("-Pesquisar Series e Filmes-");
                    System.out.println("Filtrar por:");
                    System.out.println("[1]Nome");
                    System.out.println("[2]Gênero");
                    System.out.println("[3]Idioma");
                    System.out.print(">> ");
                    int op1 = MyIO.readInt();

                    Stream opcaoEncontrada = null;

                    switch (op1) {
                        case 1:

                            System.out.print("Digite o nome: ");
                            String nome = MyIO.readLine();// Pink is the new White
                            try {
                                opcaoEncontrada = Amaze.filtrarPorNome(nome);
                            } catch (StreamNaoEncontradoException e) {
                                /*
                                 * Caso a película procurada pelo nome não tenha sido encontrada na lista, esta
                                 * exceção será capturada.
                                 */
                                System.out.println("Película não encontrada: " + e.getMessage());
                            }
                            break;
                        case 2:

                            System.out.print("Digite o gênero: ");
                            String genero = MyIO.readLine();
                            try {
                                List<Stream> midias = Amaze.filtrarPorGenero(genero);
                                Amaze.mostrarListaStream(midias);
                            } catch (StreamNaoEncontradoException e) {
                                /*
                                 * Caso a película procurada pelo gênero não tenha sido encontrada na lista,
                                 * esta exceção será capturada.
                                 */
                                System.out.println("Película não encontrada: " + e.getMessage());
                            }
                            break;
                        case 3:

                            System.out.print("Digite o idioma: ");
                            String idioma = MyIO.readLine();
                            try {
                                List<Stream> midias = Amaze.filtrarPorIdioma(idioma);
                                Amaze.mostrarListaStream(midias);
                            } catch (StreamNaoEncontradoException e) {
                                /*
                                 * Caso a película procurada pelo idioma não tenha sido encontrada na lista,
                                 * esta exceção será capturada.
                                 */
                                System.out.println("Película não encontrada: " + e.getMessage());
                            }

                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                    if (opcaoEncontrada != null) {
                        System.out.println("Película encontrada com sucesso!");
                        System.out.println("=-=-=-=-=-=-=-=-=");
                        System.out.println("[1]Marcar como opção como: já visto(a)");
                        System.out.println("[2]Adicionar opção à lista: assistir futuramente");
                        System.out.println("[0]Sair");
                        System.out.print(">> ");
                        int op1p1 = MyIO.readInt();

                        switch (op1p1) {
                            case 1:

                                try {
                                    Amaze.getClienteAtual().adicionarNaListaJaVisto((StreamAvaliavel) opcaoEncontrada);
                                    System.out.println("Película adicionada com sucesso à lista *Já Visto*!");
                                } catch (PeliculaJaExistenteException e) {
                                    /*
                                     * Caso uma película que já exista dentro da lista "Já Visto" tenha sido tentada
                                     * ser adicionada à mesma lista, esta exceção será capturada.
                                     */
                                    System.out.println("Película já existente na lista:" + e.getMessage());
                                }

                                break;
                            case 2:

                                try {
                                    Amaze.getClienteAtual().adicionarNaListaParaVer((StreamAvaliavel) opcaoEncontrada);
                                    System.out.println("Película adicionada com sucesso à lista *Ver Futuramente*!");
                                } catch (PeliculaJaExistenteException e) {
                                    /*
                                     * Caso uma película que já exista dentro da lista "Para Ver" tenha sido tentada
                                     * ser adicionada à mesma lista, esta exceção será capturada.
                                     */
                                    System.out.println("Película já existente na lista:" + e.getMessage());
                                }

                                break;
                            case 0:

                                System.out.println("Finalizando programa.");
                                break;
                            default:

                                System.out.println("Opção inválida. Tente novamente.");
                        }
                    }
                    break;

                case 2:
                    System.out.println("Selecione uma das opções abaixo: ");
                    System.out.println("[1]Mostrar lista \"PARA ASSISTIR\" de séries e filmes");
                    System.out.println("[2]Mostrar lista \"JÁ ASSISTIDO\" de séries e filmes");
                    System.out.print(">> ");
                    int op2 = MyIO.readInt();

                    switch (op2) {
                        case 1:
                            try {
                                List<StreamAvaliavel> lista = Amaze.mostrarListaParaAssistir();
                                Amaze.mostrarLista(lista);
                            } catch (ListaVaziaException e) {
                                /*
                                 * Caso a lista esteja vazia, esta exceção será capturada.
                                 */
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 2:
                            try {
                                List<StreamAvaliavel> lista = Amaze.mostrarListaJaVista();
                                Amaze.mostrarLista(lista);
                            } catch (ListaVaziaException e) {
                                /*
                                 * Caso a lista esteja vazia, esta exceção será capturada.
                                 */
                                System.out.println(e.getMessage());
                                break;
                            }

                            System.out.println("[1]Avaliar mídia");
                            System.out.println("[0]Sair");
                            int op2p2 = MyIO.readInt();

                            switch (op2p2) {
                                case 1:

                                    while (true) {
                                        System.out.print("Digite o ID da mídia: ");
                                        int inserirId = MyIO.readInt();

                                        System.out.print("Insira a nota (entre 1 e 5): ");
                                        float inserirNota = MyIO.readFloat();

                                        while (inserirNota < 1 && inserirNota > 5) {
                                            if (inserirNota < 1 && inserirNota > 5) {
                                                System.out.println("Digite uma nota válida!");
                                                inserirNota = MyIO.readFloat();
                                            }
                                        }

                                        try {
                                            Amaze.avaliar(inserirId, inserirNota);
                                            System.out.println("Avaliação registrada com sucesso!");
                                        } catch (PeliculaJaAvaliadaException e) {
                                            /*
                                             * Caso haja tentativa de avaliação mas a mesma já tenha sido registrada
                                             * anteriormente, esta exceção será capturada.
                                             */
                                            System.out.println(e.getMessage());
                                        }

                                        break;
                                    }

                                case 0:

                                    System.out.println("Finalizando programa.");
                                    break;
                                default:

                                    System.out.println("Opção inválida. Tente novamente.");
                            }
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;
                case 3:
                    System.out.println("Selecione uma das opções abaixo: ");
                    System.out.println("[1]Cadastrar Série");
                    System.out.println("[2]Cadastrar Filme");
                    System.out.print(">> ");
                    int op3 = MyIO.readInt();

                    String nomeColecao;
                    int generoColecao;
                    int idiomaColecao;
                    String dataLancamentoColecao;

                    int novoId = 0;

                    ArquivoTextoEscrita escrita = null;

                    switch (op3) {
                        case 1:
                            System.out.print("Digite o nome da série: ");
                            nomeColecao = MyIO.readLine();

                            System.out.println("Digite o gênero da série: ");
                            int contador = 0;
                            for (String genero : Stream.generos) {
                                System.out.println(contador + " - para " + genero);
                                contador++;
                            }
                            generoColecao = MyIO.readInt();

                            System.out.print("Digite o idioma da série: ");
                            contador = 0;
                            for (String idioma : Stream.idiomas) {
                                System.out.println(contador + " - para " + idioma);
                                contador++;
                            }
                            idiomaColecao = MyIO.readInt();

                            System.out.print("Digite a data de lançamento da série: ");
                            dataLancamentoColecao = MyIO.readLine();

                            System.out.print("Digite o numero de episódios da série: ");
                            int numEpisodios = MyIO.readInt();

                            novoId = Stream.contId++;

                            Serie novaSerie = new Serie(novoId, nomeColecao, generoColecao, idiomaColecao,
                                    dataLancamentoColecao, numEpisodios);
                            Amaze.adicionarColecao(novaSerie);

                            String escreverSerie = Integer.toString(novoId) + ";" + nomeColecao + ";"
                                    + dataLancamentoColecao;
                            escrita = new ArquivoTextoEscrita("codigo/src/POO_Series.csv");
                            escrita.escrever(escreverSerie);
                            escrita.fecharArquivo();

                            break;
                        case 2:
                            System.out.print("Digite o nome do filme: ");
                            nomeColecao = MyIO.readLine();

                            System.out.println("Digite o gênero do filme: ");
                            int contador1 = 0;
                            for (String genero : Stream.generos) {
                                System.out.println(contador1 + " - para " + genero);
                                contador1++;
                            }
                            generoColecao = MyIO.readInt();

                            System.out.print("Digite o idioma da série: ");
                            contador1 = 0;
                            for (String idioma : Stream.idiomas) {
                                System.out.println(contador1 + " - para " + idioma);
                                contador1++;
                            }
                            idiomaColecao = MyIO.readInt();

                            System.out.print("Digite a data de lançamento do filme: ");
                            dataLancamentoColecao = MyIO.readLine();

                            System.out.print("Digite a duração do filme (em minutos): ");
                            float duracao = MyIO.readFloat();

                            novoId = Stream.contId++;

                            Filme novoFilme = new Filme(novoId, nomeColecao, generoColecao, idiomaColecao,
                                    dataLancamentoColecao, duracao);
                            Amaze.adicionarColecao(novoFilme);

                            String escreverFilme = Integer.toString(novoId) + ";" + nomeColecao + ";"
                                    + dataLancamentoColecao + ";" + Float.toString(duracao);
                            escrita = new ArquivoTextoEscrita("codigo/src/POO_Filmes.csv");
                            escrita.escrever(escreverFilme);
                            escrita.fecharArquivo();

                            break;
                        case 0:
                            System.out.println("\n\nFinalizando programa.\n\n");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;
                case 4:
                    boolean novoAcesso = false;
                    while (novoAcesso != true) {
                        System.out.println("=-Realizar Login-=");
                        System.out.print("Login>> ");
                        String login = MyIO.readString(); // "Rog165837"
                        System.out.print("Senha>> ");
                        String senha = MyIO.readString(); // "RMor07441"
                        novoAcesso = (Amaze.loginPlataforma(login, senha) == true ? true : false);
                    }
                case 5:
                    System.out.print("Digite o nome do cliente: ");
                    String nomeCliente = MyIO.readLine();
                    System.out.print("Digite o login do cliente: ");
                    String loginCliente = MyIO.readLine();
                    System.out.print("Digite a senha do cliente: ");
                    String senhaCliente = MyIO.readLine();

                    Cliente novoCliente = new Cliente(nomeCliente, loginCliente, senhaCliente);
                    Amaze.adicionarCliente(novoCliente);

                    String escreverCliente = nomeCliente + ";" + loginCliente + ";" + senhaCliente;
                    escrita = new ArquivoTextoEscrita("codigo/src/POO_Espectadores.csv");
                    escrita.escrever(escreverCliente);
                    escrita.fecharArquivo();

                    break;
                case 6:

                    System.out.println("Mostrar: ");
                    System.out.println("[1]O cliente que assistiu mais mídias;");
                    System.out.println("[2]O cliente que tem mais avaliações;");
                    System.out.println("[3]A porcentagem dos clientes com pelo menos 15 avaliações;");
                    System.out.println("[4]As 10 mídias de melhor avaliação, com pelo menos 100 avaliações;");
                    System.out.println("[5]As 10 mídias com mais visualizações;");
                    System.out.println(
                            "[6]As 10 mídias de melhor avaliação, com pelo menos 100 avaliações, separadas por gênero;");
                    System.out.println("[7]As 10 mídias com mais visualizações, separadas por gênero.");
                    System.out.println("[0]Finalizar programa.");
                    int op6p1 = MyIO.readInt();
                    Relatorio relatorio = new Relatorio(Amaze);

                    switch (op6p1) {
                        case 1:
                            relatorio.gerarRelatorioClienteMaisMidias();

                            System.out.println(
                                    "O cliente que assistiu mais mídias é: " + relatorio.getClienteComMaisMidias());
                            System.out.println("Ele assistiu " + relatorio.getMaiorNumeroDeMidias() + " mídias.");

                            break;
                        case 2:

                            relatorio.gerarRelatorioClienteMaisAvaliacoes();
                            System.out.println(
                                    "O cliente com mais avaliações é: " + relatorio.getClienteComMaisAvaliacoes());
                            System.out.println("Ele fez " + relatorio.getMaiorNumeroDeAvaliacoes() + " avaliações.");

                            break;
                        case 3:

                            relatorio.gerarRelatorioPorcentagemClientesComPeloMenos15Avaliacoes();

                            System.out.println("A porcentagem de clientes com pelo menos 15 avaliações é: "
                                    + relatorio.getPorcentagemClientesComPeloMenos15Avaliacoes() + "%");

                            break;
                        case 4:

                            relatorio.gerarRelatorioTop10Midias();

                            System.out.println("As 10 mídias de melhor avaliação com pelo menos 100 avaliações são:");
                            for (StreamAvaliavel stream : relatorio.getTop10Midias()) {
                                System.out.println(
                                        stream.getNome() + " - Média de avaliações: " + stream.getMediaDeAvaliacoes());
                            }
                            break;
                        case 5:

                            relatorio.gerarRelatorioTop10MidiasComMaisVisualizacoes();

                            System.out.println("As 10 mídias com mais visualizações são:");
                            for (StreamAvaliavel stream : relatorio.getTop10MidiasComMaisVisualizacoes()) {
                                System.out.println(stream.getNome() + " - Número de visualizações: "
                                        + stream.getNumeroDeVisualizacoes());
                            }

                            break;
                        case 6:
                            Map<String, List<StreamAvaliavel>> top10MidiasPorGenero = relatorio
                                    .gerarRelatorioTop10MidiasPorGenero();
                            for (Map.Entry<String, List<StreamAvaliavel>> entry : top10MidiasPorGenero.entrySet()) {
                                String genero = entry.getKey();
                                List<StreamAvaliavel> top10MidiasDoGenero = entry.getValue();
                                System.out.println("Gênero " + genero + ": as 10 mídias");
                                for (StreamAvaliavel stream : top10MidiasDoGenero) {
                                    System.out.println(stream.getNome() + " - Média de avaliações: "
                                            + stream.getMediaDeAvaliacoes());
                                }
                            }

                            break;

                        case 7:

                            Map<String, List<StreamAvaliavel>> top10MidiasComMaisVisualizacoesPorGenero = relatorio
                                    .gerarRelatorioTop10MidiasComMaisVisualizacoesPorGenero();
                            for (Map.Entry<String, List<StreamAvaliavel>> entry : top10MidiasComMaisVisualizacoesPorGenero
                                    .entrySet()) {
                                String genero = entry.getKey();
                                List<StreamAvaliavel> top10MidiasDoGenero = entry.getValue();
                                System.out.println("\n Gênero " + genero + ": as 10 mídias com mais visualizações ");
                                for (StreamAvaliavel stream : top10MidiasDoGenero) {
                                    System.out.println(stream.getNome() + " - Número de visualizações: "
                                            + stream.getNumeroDeVisualizacoes());
                                }
                            }

                            break;

                        case 0:
                            System.out.println("\n\nFinalizando programa.\n\n");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;

            }

        } while (op != 0);
    }
}
