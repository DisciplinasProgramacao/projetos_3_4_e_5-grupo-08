package app;

public class Aplicacao {

	public static void carregarDadosS(Serie[] vetorDeSeries) {
		ArquivoTextoLeitura file = new ArquivoTextoLeitura("POO_Series.csv");

		String dadosS = file.ler();

		String dadosSeparadosS[] = dadosS.split(";");
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

		String dadosSeparadosF[] = dadosF.split(";");
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

		String dadosSeparadosA[] = dadosA.split(";");
		//Login;F/A;IdSerie

		while (dadosA != null) {
			for (int cont = 0; cont < vetorDeEspectadores.length; cont++) {
				if (dadosSeparadosA[0] == vetorDeEspectadores[cont].getLogin()) {
					for ( int cont2 = 0; cont < vetorDeSerie.length; cont2++){
						if (vetorDeSerie[cont2].getIdSerie == dadosSeparadosA[2]){
							if (dadosSeparadosA[1] == "F"){
								vetorDeEspectadores[cont].adicionarNaListaParaVer(vetorDeSerie[cont2]);
							} else /* dadosSeparadosA[1] == "A" */{
								vetorDeEspectadores[cont].adicionarNaListaJaVisto(vetorDeSerie[cont2]);
							}

							//adicionar serie à F/A de cliente
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
		String dadosSeparadosE[] = dadosE.split(";");

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
		//arregando dados do arquivo "POO_Espectadores.csv" para o vetor de Series
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
		for(int i = 0; i < vetorSeries.length; i++){
			Amaze.adicionarSerie(vetorSeries[i]);
		}
		//Adicionando *filmes* à Amaze
		for(int i = 0; i < vetorSeries.length; i++){
			Amaze.adicionarFilme(vetorFilmes[i]);
		}
		//Adicionando *clientes* à Amaze
		for(int i = 0; i < vetorSeries.length; i++){
			Amaze.adicionarCliente(vetorClientes[i]);
		}

		//AUDIENCIA

		carregarDadosA(vetorClientes,vetorSeries);

		int op;
		do {
			System.out.println("=-=-=-=-=-=-=-=-"+Amaze.getNome()+"=-=-=-=-=-=-=-=");
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			System.out.println("Digite uma das opções abaixo:");
			System.out.println("[1]Adicionar série à sua Lista"); //Adicionar series "para assistir"
			System.out.println("[2]Marcar série como *já assistida*"); //Marcar series "já assistidas" e retornar "lista de series ja assistidas"
			System.out.println("[3]Filtar séries"); //Buscar por filtros (nome,genero ou idioma) series em suas listas ("ja assistidas" ou "para assistir")
			System.out.println("[0]Finalizar programa");
			System.out.print(">> ");
			op = MyIO.readInt();

			switch (op) {
				case 1:

					break;
				case 2:

					break;
				case 3:
					System.out.println("Filtrar por:");
					System.out.println("[1]Nome");
					System.out.println("[2]Gênero");
					System.out.println("[3]Idioma");
					System.out.println("[4]Quantidade de Episódios");
					System.out.print(">> ");
					int op3 = MyIO.readInt();

					switch (op3){
						case 1:
							System.out.print("Digite o nome da série: ");
							String nome = MyIO.readString();
							Amaze.filtrarSeriesPorNome(nome);
							break;

						case 2:
							System.out.print("Digite o gênero: ");
							String genero = MyIO.readString();
							Amaze.filtrarSeriesPorGenero(genero);
							break;
						case 3:
							System.out.print("Digite o idioma: ");
							String idioma = MyIO.readString();
							Amaze.filtrarSeriesPorIdioma(idioma);
							break;
						case 4:
							System.out.print("Digite a quantidade de episódios: ");
							int qntEp = MyIO.readInt();
							Amaze.filtrarSeriesPorQtdEpisodios(qntEp);
							break;
						default:
							System.out.println("Opção inválida.");
					}
					break;
				case 0:
					System.out.println("Finalizando programa.");
					break;
				default:
					System.out.println("Opção inválida.");
			}

		}while (op != 3);

		//Menu Cliente


	}

}

