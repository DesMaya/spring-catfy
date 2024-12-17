package maya.catfy.principal;

import maya.catfy.model.Artista;
import maya.catfy.model.Musica;
import maya.catfy.model.TipoArtista;
import maya.catfy.repository.ArtistaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private final Scanner sc = new Scanner(System.in);
    private final ArtistaRepository repositorio;

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao!= 9) {
            var menu = """
                    *** CatFy Músicas ***                    
                                        
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                    5- Pesquisar dados sobre um artista
                                    
                    9 - Sair
                    """;

            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    pesquisarDadosDoArtista();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void pesquisarDadosDoArtista() {
    }


    private void buscarMusicasPorArtista() {
    }


    private void listarMusicas() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(System.out::println);
    }


    private void cadastrarMusicas() {
        System.out.println("Cadastrar música de que artista? ");
        var nome = sc.nextLine();
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);

        if (artista.isPresent()) {
            System.out.println("Qual o nome da música: ");
            var nomeMusica = sc.nextLine();
            Musica musica = new Musica(nomeMusica);

            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);

            repositorio.save(artista.get());
        } else {
            System.out.println("Artista não encontrado!");
        }
    }


    private void cadastrarArtistas() {
        var cadastrarNovo = "S";

        while (cadastrarNovo.equalsIgnoreCase("S")) {
            System.out.println("Informe o nome do Artista: ");
            var nome = sc.nextLine();

            System.out.println("Informe o tipo desse artista (solo, dupla ou banda): ");
            var tipo = sc.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());

            Artista artista = new Artista(nome, tipoArtista);
            repositorio.save(artista);

            System.out.println("Cadastrar novo artista? (S/N)");
            cadastrarNovo = sc.nextLine();
        }
    }
}
