package app;

public class Aplicacao {

	public static void carregarDadosS(Serie[] vetorDeSeries) {
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

	public static void carregarDadosF(Filme[] vetorDeFilmes) {
		ArquivoTextoLeitura file = new ArquivoTextoLeitura("POO_Filmes.csv");

		String dadosF = file.ler();
		dadosF = file.ler(); //pula linha

		String[] dadosSeparadosF = dadosF.split(";");
		//IdFilme;Nome;DataDeLançamento;Duração(min)

		int cont = 0;

		while (dadosF != null) {

			Filme novoFilme = new Filme(Integer.parseInt(dadosSeparadosF[0]),dadosSeparadosF[1],dadosSeparadosF[2],Integer.parseInt(dadosSeparadosF[3]));
			vetorDeFilmes[cont++] = novoFilme;
			dadosF = file.ler();
		}

		file.fecharArquivo();
	}

	public static void carregarDadosA(Cliente[] vetorDeEspectadores, Serie[] vetorDeSerie) {
		ArquivoTextoLeitura file = new ArquivoTextoLeitura("POO_Audiencia.csv");

		String dadosA = file.ler();

		String[] dadosSeparadosA = dadosA.split(";");
		//Login;F/A;IdSerie

		while (dadosA != null) {
			for (Cliente cliente : vetorDeEspectadores) {
				if (dadosSeparadosA[0].equals(cliente.getLogin())) {
					for (Serie serie : vetorDeSerie) {
						if (serie.getIdSerie == dadosSeparadosA[2]) {
							if (dadosSeparadosA[1].equals("F")) {
								cliente.adicionarNaListaParaVer(serie);
							} else /* dadosSeparadosA[1] == "A" */ {
								cliente.adicionarNaListaJaVisto(serie);
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

		//ESPECTADORES
		//Criando vetor de clientes
		Cliente[] vetorClientes = new Cliente[51893];
		//Carregando dados do arquivo "POO_Espectadores.csv" para o vetor de Series
		carregarDadosE(vetorClientes);

		//SERIE
		//Criando vetor de series
		Serie[] vetorSeries = new Serie[131];
		//Carregando dados do arquivo "POO_Series.csv" para o vetor de Series
		carregarDadosS(vetorSeries);

		//FILME
		//Criando vetor de filmes
		Filme[] vetorFilmes = new Filme[203];
		//Carregando dados do arquivo "POO_Filmes.csv" para o vetor de Series
		carregarDadosF(vetorFilmes);


		//PLATAFORMA
		//Criando plataforma de streaming "Amaze"
		PlataformaStreaming Amaze = new PlataformaStreaming("Amaze");
		//Adicionando *series* à Amaze
		for (Serie serie : vetorSeries) {
			Amaze.adicionarSerie(serie);
		}
		//Adicionando *filmes* à Amaze
		for (Filme filme : vetorFilmes) {
			Amaze.adicionarFilme(filme);
		}
		//Adicionando *clientes* à Amaze
		for (Cliente cliente : vetorClientes) {
			Amaze.adicionarCliente(cliente);
		}

		//AUDIENCIA
		//Carregando dados do arquivo "POO_Audiencia.csv"
		carregarDadosA(vetorClientes,vetorSeries);

		//REALIZAR LOGIN
		Amaze.login("Ada10","ABro14415");


		int op;
		do {
			System.out.println("=-"+ Amaze.getNome() +"-=");
			System.out.println("Olá " + Amaze.getNomeClienteAtual() + "!");
			System.out.println("Digite uma das opções abaixo:");
			System.out.println("[1]Catálogo"); //Adicionar series "para assistir"
			System.out.println("[2]Perfil"); //Marcar series "já assistidas" e retornar "lista de series ja assistidas"
			System.out.println("[0]Finalizar programa");
			System.out.print(">> ");
			op = MyIO.readInt();

			switch (op) {
				case 1:
					System.out.println("[1]Pesquisar série");
					System.out.println("[2]Pesquisar filme");
					int op1 = MyIO.readInt();

					switch (op1){
						case 1:
							System.out.println("Filtrar por:");
							System.out.println("[1]Nome");
							System.out.println("[2]Gênero");
							System.out.println("[3]Idioma");
							System.out.println("[4]Quantidade de Episódios");
							System.out.print(">> ");
							op1 = MyIO.readInt();

							Serie serieEncontrada;

							switch (op1) {
								case 1:
									System.out.print("Digite o nome da série: ");
									String nome = MyIO.readString();
									serieEncontrada = Amaze.filtrarSeriesPorNome(nome);
									break;

								case 2:
									System.out.print("Digite o gênero: ");
									String genero = MyIO.readString();
									serieEncontrada = Amaze.filtrarSeriesPorGenero(genero);
									break;
								case 3:
									System.out.print("Digite o idioma: ");
									String idioma = MyIO.readString();
									serieEncontrada = Amaze.filtrarSeriesPorIdioma(idioma);
									break;
								case 4:
									System.out.print("Digite a quantidade de episódios: ");
									int qntEp = MyIO.readInt();
									serieEncontrada = Amaze.filtrarSeriesPorQtdEpisodios(qntEp);
									break;
								default:
									System.out.println("Opção inválida.");
							}
							System.out.println("[1]Marcar como série já vista");
							System.out.println("[2]Adicionar a série à lista para assistir futuramente");
							System.out.println("[0]Sair");
							op1 = MyIO.readInt();

							switch (op1){
								case 1:
									Amaze.getClienteAtual().adicionarNaListaJaVisto(serieEncontrada);
									break;
								case 2:
									Amaze.getClienteAtual().adicionarNaListaParaVer(serieEncontrada);
									break;
								case 0:
									System.out.println("Finalizando programa.");
									break;
								default:
									System.out.println("Opção inválida.");
							}
							break;
						case 2:
							System.out.println("Filtrar por:");
							System.out.println("[1]Nome");
							System.out.println("[2]Gênero");
							System.out.println("[3]Idioma");
							System.out.print(">> ");
							op1 = MyIO.readInt();
							
							Filme filmeEncontrado;
							
							switch (op1){
								case 1:
									
									break;
								case 2:
									
									break;
								case 3:
									
									break;
								default:
									System.out.println("Opção inválida.");
							}
							
							break;
						default:
							System.out.println("Opção inválida.");
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
							System.out.println("Opção inválida.");
					}
					break;
				case 3:


					break;
				case 0:
					System.out.println("Finalizando programa.");
					break;
				default:
					System.out.println("Opção inválida.");
			}

		}while (op != 3);
	}
}

