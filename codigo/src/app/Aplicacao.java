package app;

/*
Projetos 3, 4 e 5 (PARTE 3)

Grupo 08 do laboratório da disciplina LPM 1/2023 PUC Minas - Praça da Liberdade

Alunos integrantes da equipe:
- Bernardo Cavanellas Biondini
- João Vitor Bessa Lacerda
- Nathan Gonçalves de Oliveira

Professores responsáveis:
- João Caram Santos de Oliveira
*/

public class Aplicacao {

    //Método para carregar séries à plataforma
    public static void carregarDadosS(Stream[] vetorDeSeries) {
        ArquivoTextoLeitura file = new ArquivoTextoLeitura("codigo/src/POO_Series.csv");

        String dadosS = file.ler();
        dadosS = file.ler(); //pula linha

        String[] dadosSeparadosS;
        //ID;Nome;Data

        int cont = 0;

         do{
            dadosSeparadosS = dadosS.split(";");
            Stream novaSerie = new Serie(Integer.parseInt(dadosSeparadosS[0]), dadosSeparadosS[1], dadosSeparadosS[2]);
            vetorDeSeries[cont++] = novaSerie;
            dadosS = file.ler();
            
        }while (dadosS != null);

        file.fecharArquivo();
    }

    //Método para carregar filmes à plataforma
    public static void carregarDadosF(Stream[] vetorDeFilmes) {
        ArquivoTextoLeitura file = new ArquivoTextoLeitura("codigo/src/POO_Filmes.csv");

        String dadosF = file.ler();
        dadosF = file.ler(); //pula linha

        String[] dadosSeparadosF;
        //IdFilme;Nome;DataDeLançamento;Duração(min)

        int cont = 0;

        do{
            dadosSeparadosF = dadosF.split(";");
            Stream novoFilme = new Filme(Integer.parseInt(dadosSeparadosF[0]), dadosSeparadosF[1], dadosSeparadosF[2], Float.parseFloat(dadosSeparadosF[3]));
            vetorDeFilmes[cont++] = novoFilme;
            dadosF = file.ler();
            
        }while (dadosF != null);

        file.fecharArquivo();
    }

    //Método para carregar dados de audiência de série a clientes cadastrados na plataforma
    public static void carregarDadosA(PlataformaStreaming plataforma) {
        ArquivoTextoLeitura file = new ArquivoTextoLeitura("codigo/src/POO_Audiencia.csv");

        String dadosA = file.ler();

        String[] dadosSeparadosA;
        //Login;F/A;IdSerie

         do{
            dadosSeparadosA = dadosA.split(";");
            for (Cliente cliente : plataforma.getClientes()) {
                if (dadosSeparadosA[0].equals(cliente.getLogin())) {
                    for (Stream serie : plataforma.getColecao()) {
                        if (serie.getId() == Integer.parseInt(dadosSeparadosA[2])) {
                            if (dadosSeparadosA[1].equals("F")) {
                                cliente.adicionarNaListaParaVer(serie);
                            } else /* dadosSeparadosA[1] == "A" */ {
                                cliente.adicionarNaListaJaVisto(serie);
                                plataforma.registrarAudiencia(serie);
                            }
                        }
                    }
                }
                dadosA = file.ler();
            }

            file.fecharArquivo();
        }while (dadosA != null);
    }

    //Método para criar perfis de cliente na plataforma
    public static void carregarDadosE(Cliente[] vetorDeEspectadores) {
        ArquivoTextoLeitura file = new ArquivoTextoLeitura("codigo/src/POO_Espectadores.csv");

        String dadosE = file.ler();

        String[] dadosSeparadosE;

        int cont = 0;

        do {
            //Nome;Login;Senha
            dadosSeparadosE = dadosE.split(";");
            Cliente novoCliente = new Cliente(dadosSeparadosE[0], dadosSeparadosE[1], dadosSeparadosE[2]);
            vetorDeEspectadores[cont++] = novoCliente;
            dadosE = file.ler();
            
        }while (dadosE != null);

        file.fecharArquivo();
    }

    public static void main(String[] args) {

        //PLATAFORMA
        //Criando plataforma de streaming "Amaze"
        PlataformaStreaming Amaze = new PlataformaStreaming("Amaze");


        //ESPECTADORES
        //Criando vetor de clientes
        Cliente[] vetorClientes = new Cliente[51893];
        //Carregando dados do arquivo "POO_Espectadores.csv" para o vetor de Series
        carregarDadosE(vetorClientes);
        //Adicionando *clientes* à Amaze
        for (Cliente cliente : vetorClientes) {
            Amaze.adicionarCliente(cliente);
        }


        int idMaior = -1;

        //SERIE
        //Criando vetor colecao de series
        Serie[] vetorSeries = new Serie[129];
        //Carregando dados do arquivo "POO_Series.csv" para o vetor de colecao
        carregarDadosS(vetorSeries);
        //Adicionando *series* à Amaze
        for (Serie serie : vetorSeries) {
            Amaze.adicionarColecao(serie);
            if (serie.getId() > idMaior) {
                idMaior = serie.getId();
            }
        }


        //AUDIENCIA (SERIES)
        //Carregando dados do arquivo "POO_Audiencia.csv"
        carregarDadosA(Amaze);


        //FILME
        //Criando vetor de filmes
        Filme[] vetorFilmes = new Filme[203];
        //Carregando dados do arquivo "POO_Filmes.csv" para o vetor de Series
        carregarDadosF(vetorFilmes);
        //Adicionando *filmes* à Amaze
        for (Filme filme : vetorFilmes) {
            Amaze.adicionarColecao(filme);
            if (filme.getId() > idMaior) {
                idMaior = filme.getId();
            }
        }


        //REALIZAR LOGIN
        Amaze.login("Ada10", "ABro14415");


        //MENU
        int op;
        do {
            System.out.println("=-" + Amaze.getNome() + "-=");
            System.out.println("Olá " + Amaze.getNomeClienteAtual() + "!");
            System.out.println("Digite uma das opções abaixo:");
            System.out.println("[1]Catálogo"); //Pesquisar series e filmes -> Aicionar "para assistir" ou "já assistido"
            System.out.println("[2]Perfil"); //Marcar series "já assistidas" e retornar "lista de series ja assistidas"
            System.out.println("[3]Adicionar série ou filme ao catálogo");
            System.out.println("[0]Finalizar programa");
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
                            /* System.out.print("Digite o nome: ");
                            String nome = MyIO.readString();
                            Amaze.filtrarPorNome(nome);
                            System.out.println("Selecione a película inserindo seu id: ");
                            op1 = MyIO.readInt();

                            do{
                                
                                if(op1 == Amaze.filtrarPorNome){
                                    opcaoEncontrada = ;
                                }
                            }while (op1 != null);
                            break;*/

                        case 2:
                            /*System.out.print("Digite o gênero: ");
                            String genero = MyIO.readString();
                            Amaze.filtrarPorGenero(genero);

                            break;*/
                        case 3:
                            /*System.out.print("Digite o idioma: ");
                            String idioma = MyIO.readString();
                            Amaze.filtrarPorIdioma(idioma);

                            break;*/
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                    System.out.println("[1]Marcar como opção como: já visto(a)");
                    System.out.println("[2]Adicionar opção à lista: assistir futuramente");
                    System.out.println("[0]Sair");
                    op1 = MyIO.readInt();

                    switch (op1) {
								/*case 1:
								Amaze.getClienteAtual().adicionarNaListaJaVisto(opcaoEncontrada);
								break;
								case 2:
								Amaze.getClienteAtual().adicionarNaListaParaVer(opcaoEncontrada);
								break;*/
                        case 0:
                            System.out.println("Finalizando programa.");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
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
                            Amaze.getClienteAtual().mostrarListaParaAssistir();
                            break;
                        case 2:
                            Amaze.getClienteAtual().mostrarListaJaVista();
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
                    String generoColecao;
                    String idiomaColecao;
                    String dataLancamentoColecao;

                    switch (op3) {
                        case 1:
                            System.out.print("Digite o nome da série: ");
                            nomeColecao = MyIO.readString();

                            System.out.print("Digite o gênero da série: ");
                            generoColecao = MyIO.readString();

                            System.out.print("Digite o idioma da série: ");
                            idiomaColecao = MyIO.readString();

                            System.out.print("Digite a data de lançamento da série: ");
                            dataLancamentoColecao = MyIO.readString();

                            System.out.print("Digite o numero de episódios da série: ");
                            int numEpisodios = MyIO.readInt();

                            Serie novaSerie = new Serie(idMaior++, nomeColecao, generoColecao, idiomaColecao, dataLancamentoColecao, numEpisodios);
                            Amaze.adicionarColecao(novaSerie);


                            break;
                        case 2:
                            System.out.print("Digite o nome do filme: ");
                            nomeColecao = MyIO.readString();

                            System.out.print("Digite o gênero do filme: ");
                            generoColecao = MyIO.readString();

                            System.out.print("Digite o idioma do filme: ");
                            idiomaColecao = MyIO.readString();

                            System.out.print("Digite a data de lançamento do filme: ");
                            dataLancamentoColecao = MyIO.readString();

                            System.out.print("Digite o numero de episódios do filme: ");
                            float duracao = MyIO.readFloat();

                            Filme novoFilme = new Filme(idMaior++, nomeColecao, generoColecao, idiomaColecao, dataLancamentoColecao, duracao);
                            Amaze.adicionarColecao(novoFilme);
                            break;
                        case 0:
                            System.out.println("Finalizando programa.");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;
            }

        } while (op != 0);
    }
}

