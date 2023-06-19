package app;

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
            try {
                if (plataforma.getClienteAtual() != null && stream != null) {
                    if (dadosSeparadosA[1].equals("F")) {
                        try {
                            plataforma.adicionarNaListaParaVer((StreamAvaliavel) stream);
                        } catch (PeliculaJaExistenteException e) {
                            cont++;
                        }
                    } else /* dadosSeparadosA[1] == "A" */ {
                        plataforma.registrarAudiencia((StreamAvaliavel) stream);
                    }
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
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
        // Nome;Login;Senha

        do {
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
    public static void carregarDadosT(PlataformaStreaming plataforma) {
        ArquivoTextoLeitura file = new ArquivoTextoLeitura("codigo/src/POO_Trailers.csv");

        String dadosF = file.ler();
        dadosF = file.ler(); // pula linha

        String[] dadosSeparadosF;
        // IdFilme;Nome;DataDeLançamento;Duração(min)

        do {
            dadosSeparadosF = dadosF.split(";");
            Stream novoFilme = new Trailer(Integer.parseInt(dadosSeparadosF[0]), dadosSeparadosF[1],
                    dadosSeparadosF[2]);
            plataforma.adicionarColecao(novoFilme);
            dadosF = file.ler();

        } while (dadosF != null);

        file.fecharArquivo();
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

        // FILME
        // Carregando dados do arquivo "POO_Filmes.csv" para a coleção
        carregarDadosF(Amaze);

        // TRAILERS
        // Carregando dados do arquivo "POO_Trailers.csv" para a coleção
        carregarDadosT(Amaze);

        // AUDIENCIA (SERIES)
        // Carregando dados do arquivo "POO_Audiencia.csv"
        carregarDadosA(Amaze);

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

        Menu menu = new Menu(Amaze);
        menu.menu();
    }
}
