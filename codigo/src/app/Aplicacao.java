package app;

public class Aplicacao {

	public static void main(String[] args) {
		
		//Armazenar dados de cliente
		//Armazenar dados de series
		
		int op;
		do {	
			System.out.println("=-=-=-=-=-=-=-=-MENU=-=-=-=-=-=-=-=");
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
