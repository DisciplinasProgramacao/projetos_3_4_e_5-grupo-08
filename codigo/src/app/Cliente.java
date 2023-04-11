import java.util.List;

public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    List<Serie> listaParaVer;
    List<Serie> listaJaVistas;


    public Cliente(String nomeDeUsuario, String senha, List<Serie> listaParaVer, List<Serie> listaJaVistas) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.listaParaVer = listaParaVer;
        this.listaJaVistas = listaJaVistas;
    }

    public String getNomeDeUsuario() {
        return this.nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void adicionarNaLista(Serie serie) {
        this.listaParaVer.add(serie);
    }

    public void retirarDaLista(String nomeSerie) {
        int index = 0;

        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getNome() == nomeSerie) index = i;
        }

        this.listaParaVer.remove(index);
    }

    public List<Serie> filtrarPorGenero(String genero) {
        List<Serie> lista = null;

        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getGenero() == genero) lista.add(this.listaJaVistas.get(i));
        }

        for(int i = 0; i < this.listaParaVer.size(); i++) {
            if(this.listaParaVer.get(i).getGenero() == genero) lista.add(this.listaParaVer.get(i));
        }

        return lista;
    }

    public List<Serie> filtrarPorIdioma(String idioma) {
        List<Serie> lista = null;

        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getIdioma() == idioma) lista.add(this.listaJaVistas.get(i));
        }

        for(int i = 0; i < this.listaParaVer.size(); i++) {
            if(this.listaParaVer.get(i).getIdioma() == idioma) lista.add(this.listaParaVer.get(i));
        }

        return lista;
    }

    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        List<Serie> lista = null;

        for(int i = 0; i < this.listaJaVistas.size(); i++) {
            if(this.listaJaVistas.get(i).getEpisodios() == quantEpisodios) lista.add(this.listaJaVistas.get(i));
        }

        for(int i = 0; i < this.listaParaVer.size(); i++) {
            if(this.listaParaVer.get(i).getEpisodios() == quantEpisodios) lista.add(this.listaParaVer.get(i));
        }

        return lista;
    }

    public void registrarAudiencia(Serie serie) {
        this.listaJaVistas.add(serie);
    }
}
