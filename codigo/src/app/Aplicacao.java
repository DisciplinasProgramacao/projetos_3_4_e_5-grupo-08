package app;

public class Aplicacao {

	public static void carregarDadosS(Stream[] vetorDeSeries) {
		ArquivoTextoLeitura file = new ArquivoTextoLeitura("POO_Series.csv");

		String dadosS = file.ler();

		String[] dadosSeparadosS = dadosS.split(";");
		//ID;Nome;Data

		int cont = 0;

		while (dadosS != null) {

			Serie novaSerie = new Serie(Integer.parseInt(dadosSeparadosS[0]),dadosSeparadosS[1],dadosSeparadosS[2]);
			vetorDeSeries[cont++] = novaSerie;
			dadosS = file.ler();
		}

		file.fecharArquivo();
	}

	public static void carregarDadosF(Stream[] vetorDeFilmes) {
		ArquivoTextoLeitura file = new ArquivoTextoLeitura("POO_Filmes.csv");

		String dadosF = file.ler();
		dadosF = file.ler(); //pula linha

		String[] dadosSeparadosF = dadosF.split(";");
		//IdFilme;Nome;DataDeLançamento;Duração(min)

		int cont = 0;

		while (dadosF != null) {

			Stream novoFilme = new Filme(Integer.parseInt(dadosSeparadosF[0]),dadosSeparadosF[1],dadosSeparadosF[2],Float.parseFloat(dadosSeparadosF[3]));
			vetorDeFilmes[cont++] = novoFilme;
			dadosF = file.ler();
		}

		file.fecharArquivo();
	}

	public static void carregarDadosA(Cliente[] vetorDeEspectadores, Stream[] vetorDeSerie, PlataformaStreaming plataforma) {
		ArquivoTextoLeitura file = new ArquivoTextoLeitura("POO_Audiencia.csv");

		String dadosA = file.ler();

		String[] dadosSeparadosA = dadosA.split(";");
		//Login;F/A;IdSerie

		while (dadosA != null) {
			for (Cliente cliente : vetorDeEspectadores) {
				if (dadosSeparadosA[0].equals(cliente.getLogin())) {
					for (Stream serie : vetorDeSerie) {
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
		}
	}

	public static void carregarDadosE(Cliente[] vetorDeEspectadores) {
		ArquivoTextoLeitura file = new ArquivoTextoLeitura("POO_Espectadores.csv");

		String dadosE = file.ler();
		String[] dadosSeparadosE = dadosE.split(";");

		int cont = 0;

		while (dadosE != null) {
			//Nome;Login;Senha
			Cliente novoCliente = new Cliente(dadosSeparadosE[0],dadosSeparadosE[1],dadosSeparadosE[2]);
			vetorDeEspectadores[cont++] = novoCliente;
			dadosE = file.ler();
		}

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

		//SERIE
		//Criando vetor colecao de series
		Stream[] vetorSeries = new Stream[131];
		//Carregando dados do arquivo "POO_Series.csv" para o vetor de colecao
		carregarDadosS(vetorSeries);
		//Adicionando *series* à Amaze
		for (Stream serie : vetorSeries) {
			Amaze.adicionarColecao(serie);
		}

		//AUDIENCIA (SERIES)
		//Carregando dados do arquivo "POO_Audiencia.csv"
		carregarDadosA(vetorClientes,vetorSeries,Amaze);


		//FILME
		//Criando vetor de filmes
		Stream[] vetorFilmes = new Filme[203];
		//Carregando dados do arquivo "POO_Filmes.csv" para o vetor de Series
		carregarDadosF(vetorFilmes);
		//Adicionando *filmes* à Amaze
		for (Stream filme : vetorFilmes) {
			Amaze.adicionarColecao(filme);
		}

		//REALIZAR LOGIN
		Amaze.login("Ada10","ABro14415");

		int op;
		do {
			System.out.println("=-"+ Amaze.getNome() +"-=");
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

					Stream opcaoEncontrada;

					switch (op1) {
						case 1:
							System.out.print("Digite o nome: ");
							String nome = MyIO.readString();
							Amaze.filtrarPorNome(nome);

							break;

						case 2:
							System.out.print("Digite o gênero: ");
							String genero = MyIO.readString();
							Amaze.filtrarPorGenero(genero);

							break;
						case 3:
							System.out.print("Digite o idioma: ");
							String idioma = MyIO.readString();
							Amaze.filtrarPorIdioma(idioma);

							break;
						default:
							System.out.println("Opção inválida. Tente novamente.");
					}
					System.out.println("[1]Marcar como opção como: já visto(a)");
					System.out.println("[2]Adicionar opção à lista: assistir futuramente");
					System.out.println("[0]Sair");
					op1 = MyIO.readInt();

					switch (op1){
						case 1:
							Amaze.getClienteAtual().adicionarNaListaJaVisto(opcaoEncontrada);
							break;
						case 2:
							Amaze.getClienteAtual().adicionarNaListaParaVer(opcaoEncontrada);
							break;
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

					switch (op2){
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


					break;
				case 0:
					System.out.println("Finalizando programa.");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}

		}while (op != 3);
	}
}

