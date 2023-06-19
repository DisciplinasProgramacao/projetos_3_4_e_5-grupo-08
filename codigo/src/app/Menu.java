package app;

import java.util.List;
import java.util.Map;

public class Menu {
    private PlataformaStreaming Amaze;

    /**
     * Construtor padrão do menu
     * 
     * Atribui uma plataforma a ser trabalhada
     * @param amaze
     */
    public Menu(PlataformaStreaming amaze) {
        this.Amaze = amaze;
    }

    /**
     * Função principal
     * Exibe o menu e chama as funções auxiliares
     */
    public void menu() {
        int op;
        do {
            System.out.printf("=-=-=-" + Amaze.getNome() + "-=-=-=\n");
            System.out.printf("Olá, " + Amaze.getNomeClienteAtual() + "!\n");
            System.out.println("=-=-=-=-=-=-=-=-=");
            System.out.println("Digite uma das opções abaixo:");
            System.out.println("[1]Catálogo"); // Pesquisar series e filmes -> Aicionar "para assistir" ou "já assistido"
            System.out.println("[2]Perfil"); // Mostrar listas de series & filmes "já assistidas" ou "para assistir" avaliar películas "já assistidas"
            System.out.println("[3]Adicionar série/filme/trailer ao catálogo");
            System.out.println("[4]Entrar em outra conta");
            System.out.println("[5]Cadastrar cliente");
            System.out.println("[6]Relatórios");
            System.out.println("[0]Finalizar programa");
            System.out.println("=-=-=-=-=-=-=-=-=");
            System.out.print(">> ");
            op = MyIO.readInt();

            switch (op) {
                case 1:
                    this.catalogo();
                    break;

                case 2:
                    this.perfil();
                    break;
                case 3:
                    this.add();
                    break;
                case 4:
                    this.novoLogin();
                    break;
                case 5:
                    this.novoCliente();
                    break;
                case 6:
                    this.realtorios();
                    break;
                case 0:
                    System.out.println("\n\nFinalizando programa.\n\n");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (op != 0);
    }

    private void catalogo() {
        System.out.println("-Pesquisar Séries, Filmes e Trailers-");
        System.out.println("Filtrar por:");
        System.out.println("[1]Nome");
        System.out.println("[2]Gênero");
        System.out.println("[3]Idioma");
        System.out.print(">> ");
        int op = MyIO.readInt();

        Stream opcaoEncontrada = null;

        switch (op) {
            case 1:

                System.out.print("Digite o nome: ");
                String nome = MyIO.readLine();// Pink is the new White
                try {
                    opcaoEncontrada = Amaze.filtrarPorNome(nome);
                    System.out.println("\nEncontramos uma midia com esse nome:\n" + opcaoEncontrada);
                    System.out.println("Película encontrada com sucesso!");
                    System.out.println("=-=-=-=-=-=-=-=-=");
                    System.out.println("[1]Marcar como opção como: já visto(a)");
                    System.out.println("[2]Adicionar opção à lista: assistir futuramente");
                    System.out.println("[0]Sair");
                    System.out.print(">> ");
                    int op1 = MyIO.readInt();

                    switch (op1) {
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
                } catch (StreamNaoEncontradoException e) {
                    /*
                        * Caso a película procurada pelo nome não tenha sido encontrada na lista, esta
                        * exceção será capturada.
                        */
                    System.out.println("Película não encontrada: " + e.getMessage());
                }
                break;
            case 2:
                System.out.println("Digite o gênero: ");
                
                
                int contadorGenero = 0;
                for (String genero : Stream.generos) {
                    System.out.println("[" + contadorGenero + "]" + " - para " + genero);
                    contadorGenero++;
                }
                System.out.print(">> ");
                int genero = MyIO.readInt();

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

                System.out.println("Digite o idioma: ");

                int contadorIdioma = 0;
                for (String idioma : Stream.idiomas) {
                    System.out.println("[" + contadorIdioma + "]" + " - para " + idioma);
                    contadorIdioma++;
                }
                System.out.print(">> ");
                int idioma = MyIO.readInt();

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
    }

    private void perfil() {
        System.out.println("Selecione uma das opções abaixo: ");
        System.out.println("[1]Mostrar lista \"PARA ASSISTIR\" de séries e filmes");
        System.out.println("[2]Mostrar lista \"JÁ ASSISTIDO\" de séries e filmes");
        System.out.print(">> ");
        int op = MyIO.readInt();

        switch (op) {
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
                System.out.print(">> ");
                int op1 = MyIO.readInt();

                switch (op1) {
                    case 1:

                        while (true) {
                            System.out.print("Digite o ID da mídia: ");
                            int inserirId = MyIO.readInt();
                            
                            boolean idValida = false;
                            while(idValida != true){
                            for (AvaliacaoStream a : Amaze.getClienteAtual().listaJaVistas) {
                                if (a.getIdStream() == inserirId){
                                    idValida = true;
                                } 
                            }
                                if(idValida == false){
                                    System.out.println("Digite uma ID válida!");
                                    System.out.print(">> ");
                                    inserirId = MyIO.readInt();
                                }
                                    
                            }

                            System.out.print("Insira a nota (entre 1 e 5): ");
                            float inserirNota = MyIO.readFloat();

                            while (inserirNota < 1 || inserirNota > 5) {
                                    System.out.println("Digite uma nota válida!");
                                    System.out.print(">> ");
                                    inserirNota = MyIO.readFloat();
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
    }

    private void add() {
        System.out.println("Selecione uma das opções abaixo: ");
        System.out.println("[1]Cadastrar Série");
        System.out.println("[2]Cadastrar Filme");
        System.out.println("[3]Cadastrar Trailer");
        System.out.print(">> ");
        int op = MyIO.readInt();

        String nomeColecao;
        int generoColecao;
        int idiomaColecao;
        String dataLancamentoColecao;

        int novoId = 0;

        ArquivoTextoEscrita escrita = null;

        switch (op) {
            case 1:
                System.out.println("Insira os seguintes dados da série: ");   

                System.out.print("Nome: ");
                nomeColecao = MyIO.readLine();

                System.out.println("Gênero: ");
                int contador1 = 0;
                for (String genero : Stream.generos) {
                    System.out.println("[" + contador1 + "]" + " - para " + genero);
                    contador1++;
                }
                generoColecao = MyIO.readInt();

                System.out.println("Idioma: ");
                contador1 = 0;
                for (String idioma : Stream.idiomas) {
                    System.out.println(contador1 + " - para " + idioma);
                    contador1++;
                }
                System.out.print(">> ");

                idiomaColecao = MyIO.readInt();

                System.out.print("Data de lançamento: ");
                dataLancamentoColecao = MyIO.readLine();

                System.out.print("Número de episódios: ");
                int numEpisodios = MyIO.readInt();

                novoId = Stream.contId++;

                Stream novaSerie = new Serie(novoId, nomeColecao, generoColecao, idiomaColecao, dataLancamentoColecao, numEpisodios);
                Amaze.adicionarColecao(novaSerie);

                String escreverSerie = Integer.toString(novoId) + ";" + nomeColecao + ";" + dataLancamentoColecao;
                escrita = new ArquivoTextoEscrita("codigo/src/POO_Series.csv");
                escrita.escrever(escreverSerie);
                escrita.fecharArquivo();
                
                System.out.println("Série cadastrada com sucesso.");

                break;
            case 2:
                System.out.println("Insira os seguintes dados do filme: ");   

                System.out.print("Nome: ");
                nomeColecao = MyIO.readLine();

                System.out.println("Gênero: ");
                int contador2 = 0;
                for (String genero : Stream.generos) {
                    System.out.println("[" + contador2 + "]" + " - para " + genero);
                    contador2++;
                }
                generoColecao = MyIO.readInt();

                System.out.println("Idioma: ");
                contador2 = 0;
                for (String idioma : Stream.idiomas) {
                    System.out.println(contador2 + " - para " + idioma);
                    contador2++;
                }
                System.out.print(">> ");

                idiomaColecao = MyIO.readInt();

                System.out.print("Data de lançamento: ");
                dataLancamentoColecao = MyIO.readLine();

                System.out.print("Duração (em minutos): ");
                float duracao = MyIO.readFloat();

                novoId = Stream.contId++;

                Stream novoFilme = new Filme(novoId, nomeColecao, generoColecao, idiomaColecao, dataLancamentoColecao, duracao);
                Amaze.adicionarColecao(novoFilme);

                String escreverFilme = Integer.toString(novoId) + ";" + nomeColecao + ";" + dataLancamentoColecao + ";" + Float.toString(duracao);
                escrita = new ArquivoTextoEscrita("codigo/src/POO_Filmes.csv");
                escrita.escrever(escreverFilme);
                escrita.fecharArquivo();

                System.out.println("Filme cadastrado com sucesso.");

                break;
            case 3:
                System.out.println("Insira os seguintes dados do trailer: ");

                System.out.print("Nome: ");
                nomeColecao = MyIO.readLine();

                System.out.println("Gênero: ");
                int contador3 = 0;
                for (String genero : Stream.generos) {
                    System.out.println("[" + contador3 + "]" + " - para " + genero);
                    contador3++;
                }
                generoColecao = MyIO.readInt();

                System.out.println("Idioma: ");
                contador3 = 0;
                for (String idioma : Stream.idiomas) {
                    System.out.println(contador3 + " - para " + idioma);
                    contador3++;
                }
                System.out.print(">> ");
                idiomaColecao = MyIO.readInt();

                System.out.print("Data de lançamento: ");
                dataLancamentoColecao = MyIO.readLine();

                novoId = Stream.contId++;

                Stream novoTrailer = new Trailer(novoId, nomeColecao, generoColecao, idiomaColecao, dataLancamentoColecao);
                Amaze.adicionarColecao(novoTrailer);

                String escreverTrailer = Integer.toString(novoId) + ";" + nomeColecao + ";" + dataLancamentoColecao;
                escrita = new ArquivoTextoEscrita("codigo/src/POO_Trailers.csv");
                escrita.escrever(escreverTrailer);
                escrita.fecharArquivo();

                System.out.println("Trailer cadastrado com sucesso.");

                break;
            case 0:
                System.out.println("\n\nFinalizando programa.\n\n");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void novoLogin() {
        boolean novoAcesso = false;
        while (novoAcesso != true) {
            System.out.println("=-Realizar Login-=");
            System.out.print("Login>> ");
            String login = MyIO.readString(); // "Rog165837"
            System.out.print("Senha>> ");
            String senha = MyIO.readString(); // "RMor07441"
            novoAcesso = (Amaze.loginPlataforma(login, senha) == true ? true : false);
        }
    }

    private void novoCliente() {
        ArquivoTextoEscrita escrita = null;

        System.out.println("Insira os seguintes dados do cliente:");
        System.out.print("Nome: ");
        String nomeCliente = MyIO.readLine();
        System.out.print("Login: ");
        String loginCliente = MyIO.readLine();
        System.out.print("Senha: ");
        String senhaCliente = MyIO.readLine();

        Cliente novoCliente = new Cliente(nomeCliente, loginCliente, senhaCliente);
        Amaze.adicionarCliente(novoCliente);

        String escreverCliente = nomeCliente + ";" + loginCliente + ";" + senhaCliente;
        escrita = new ArquivoTextoEscrita("codigo/src/POO_Espectadores.csv");
        escrita.escrever(escreverCliente);
        escrita.fecharArquivo();

        System.out.println("Cliente cadastrado com sucesso.");
    }

    private void realtorios() {
        System.out.println("Exibir: ");
        System.out.println("[1]O cliente que assistiu mais mídias;");
        System.out.println("[2]O cliente que tem mais avaliações;");
        System.out.println("[3]A porcentagem dos clientes com pelo menos 15 avaliações;");
        System.out.println("[4]As 10 mídias de melhor avaliação, com pelo menos 100 avaliações;");
        System.out.println("[5]As 10 mídias com mais visualizações;");
        System.out.println("[6]As 10 mídias de melhor avaliação, com pelo menos 100 avaliações, separadas por gênero;");
        System.out.println("[7]As 10 mídias com mais visualizações, separadas por gênero.");
        System.out.println("[0]Finalizar programa.");
        System.out.println(">> ");
        int op = MyIO.readInt();

        Relatorio relatorio = new Relatorio(Amaze);

        switch (op) {
            case 1:
                relatorio.gerarRelatorioClienteMaisMidias();

                System.out.println("O cliente que assistiu mais mídias é: " + relatorio.getClienteComMaisMidias());
                System.out.println("Ele assistiu " + relatorio.getMaiorNumeroDeMidias() + " mídias.");

                break;
            case 2:

                relatorio.gerarRelatorioClienteMaisAvaliacoes();
                System.out.println("O cliente com mais avaliações é: " + relatorio.getClienteComMaisAvaliacoes());
                System.out.println("Ele fez " + relatorio.getMaiorNumeroDeAvaliacoes() + " avaliações.");

                break;
            case 3:

                relatorio.gerarRelatorioPorcentagemClientesComPeloMenos15Avaliacoes();
                System.out.println("A porcentagem de clientes com pelo menos 15 avaliações é: " + relatorio.getPorcentagemClientesComPeloMenos15Avaliacoes() + "%");

                break;
            case 4:

                relatorio.gerarRelatorioTop10Midias();

                System.out.println("As 10 mídias de melhor avaliação com pelo menos 100 avaliações são:");

                for (StreamAvaliavel stream : relatorio.getTop10Midias()) {
                    System.out.println(stream.getNome() + " - Média de avaliações: " + stream.getMediaDeAvaliacoes());
                }

                break;
            case 5:

                relatorio.gerarRelatorioTop10MidiasComMaisVisualizacoes();

                System.out.println("As 10 mídias com mais visualizações são:");
                
                for (StreamAvaliavel stream : relatorio.getTop10MidiasComMaisVisualizacoes()) {
                    System.out.println(stream.getNome() + " - Número de visualizações: " + stream.getNumeroDeVisualizacoes());
                }

                break;
            case 6:
                Map<String, List<StreamAvaliavel>> top10MidiasPorGenero = relatorio.gerarRelatorioTop10MidiasPorGenero();
                for (Map.Entry<String, List<StreamAvaliavel>> entry : top10MidiasPorGenero.entrySet()) {
                    String genero = entry.getKey();

                    List<StreamAvaliavel> top10MidiasDoGenero = entry.getValue();

                    System.out.println("Gênero " + genero + ": as 10 mídias");

                    for (StreamAvaliavel stream : top10MidiasDoGenero) {
                        System.out.println(stream.getNome() + " - Média de avaliações: " + stream.getMediaDeAvaliacoes());
                    }
                }

                break;

            case 7:

                Map<String, List<StreamAvaliavel>> top10MidiasComMaisVisualizacoesPorGenero = relatorio.gerarRelatorioTop10MidiasComMaisVisualizacoesPorGenero();
                for (Map.Entry<String, List<StreamAvaliavel>> entry : top10MidiasComMaisVisualizacoesPorGenero.entrySet()) {
                    String genero = entry.getKey();
                    List<StreamAvaliavel> top10MidiasDoGenero = entry.getValue();
                    System.out.println("\n Gênero " + genero + ": as 10 mídias com mais visualizações ");
                    for (StreamAvaliavel stream : top10MidiasDoGenero) {
                        System.out.println(stream.getNome() + " - Número de visualizações: " + stream.getNumeroDeVisualizacoes());
                    }
                }

                break;

            case 0:
                System.out.println("\n\nFinalizando programa.\n\n");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
}
