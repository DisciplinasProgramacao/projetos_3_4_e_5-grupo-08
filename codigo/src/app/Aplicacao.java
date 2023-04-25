package app;

public class Aplicacao {

	public static void carregarDadosS(Serie[] vetorDeSeries) {
		ArquivoTextoLeitura file = new ArquivoTextoLeitura("POO_Series.csv");

		String dadosS = file.ler();
		String dadosSeparados[] = dadosS.split(";");

		int cont = 0;

		while (dadosS != null) {
			//ID;Nome;Data
			Serie novaSerie = new Serie(Integer.parseInt(dadosSeparados[0]),dadosSeparados[1],dadosSeparados[2]);
			vetorDeSeries[cont++] = novaSerie;
			dadosS = file.ler();
		}


		file.fecharArquivo();
	}

	public static void carregarDadosF(Filme[] vetorDeFilmes) {
		ArquivoTextoLeitura file = new ArquivoTextoLeitura("POO_Filmes.csv");

		String dadosF = file.ler();
		String dadosSeparados[] = dadosF.split(";");

		int cont = 0;

		while (dadosF != null) {
			//IdFilme;Nome;DataDeLançamento;Duração(min)
			Filme novoFilme = new Filme(Integer.parseInt(dadosSeparados[0]),dadosSeparados[1],dadosSeparados[2],Integer.parseInt(dadosSeparados[3]));
			vetorDeFilmes[cont++] = novoFilme;
			dadosF = file.ler();
		}


		file.fecharArquivo();
	}

//	public static void carregarDadosA(Serie[] vetorDeSeries) {
//		ArquivoTextoLeitura file = new ArquivoTextoLeitura("POO_Audiencia.csv");
//
//		String dadosA = file.ler();
//		String dadosSeparados[] = dadosA.split(";");
//
//		int cont = 0;
//
//		while (dadosA != null) {
//			//Login;F/A;IdSerie
//			Serie novoAudiencia = new Serie(Integer.parseInt(dadosSeparados[0]),dadosSeparados[1],dadosSeparados[2]);
//			vetorDeSeries[cont++] = novaSerie;
//			dadosA = file.ler();
//		}
//
//
//		file.fecharArquivo();
//	}

	public static void carregarDadosE(Cliente[] vetorDeEspectadores) {
		ArquivoTextoLeitura file = new ArquivoTextoLeitura("POO_Espectadores.csv");

		String dadosE = file.ler();
		String dadosSeparados[] = dadosE.split(";");

		int cont = 0;

		while (dadosE != null) {
			//Nome;Login;Senha
			Cliente novoCliente = new Cliente(dadosSeparados[0],dadosSeparados[1],dadosSeparados[2]);
			vetorDeEspectadores[cont++] = novoCliente;
			dadosE = file.ler();
		}


		file.fecharArquivo();
	}

	public static void main(String[] args) {


		//Login;F/A;IdSerie
//		carregarDadosA("POO_Audiencia.csv");

		Cliente vetorClientes[] = new Cliente[51893];

		carregarDadosE(vetorClientes);

		//Criando vetor
		Serie vetorSeries[] = new Serie[131];

		//Criando filmes
		Filme vetorFilmes[] = new Filme[203];

		//Carregando dados do arquivo "POO_Series.csv" para o vetor de Series
		carregarDadosS(vetorSeries);

		//Criando plataforma de streaming "Amaze"
		PlataformaStreaming Amaze = new PlataformaStreaming("Amaze");

		//Adicionando series à Amaze
		for(int i = 0; i < vetorSeries.length; i++){
			Amaze.adicionarSerie(vetorSeries[i]);
		}

		//Adicionando filmes à Amaze
		for(int i = 0; i < vetorSeries.length; i++){
			Amaze.adicionarFilme(vetorFilmes[i]);
		}

		//Adicionando clientes à Amaze
		for(int i = 0; i < vetorSeries.length; i++){
			Amaze.adicionarCliente(vetorClientes[i]);
		}


		
		int op;
		do {	
			System.out.println("=-=-=-=-=-=-=-=-"+Amaze.getNome()+"=-=-=-=-=-=-=-=");
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			System.out.println("Digite uma das opções abaixo:");
			System.out.println("[1]Adicionar série à sua Lista");
			System.out.println("[2]Marcar série como *já assistida*");
			System.out.println("[3]Finalizar programa");
			System.out.print(">> ");
			op = MyIO.readInt();
			
			switch (op) {
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
						System.out.println("Finalizando programa.");
						break;
				default:
						System.out.println("Opção inválida.");														
			}
			
		}while (op != 3);
		
		//Menu Cliente
		//Adicionar series "para assistir"
		//Marcar series "já assistidas" e retornar "lista de series ja assistidas"
		//Buscar por filtros (nome,genero ou idioma) series em suas listas ("ja assistidas" ou "para assistir")

	}

}

