package com.example.aluracursos.challengeliteralura.principal;

import com.example.aluracursos.challengeliteralura.model.*;
import com.example.aluracursos.challengeliteralura.repositorio.IAutorRepository;
import com.example.aluracursos.challengeliteralura.repositorio.ILibroRepository;
import com.example.aluracursos.challengeliteralura.service.ConsumoAPI;
import com.example.aluracursos.challengeliteralura.service.ConvierteDatos;

import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?";
    private ConvierteDatos conversor = new ConvierteDatos();
    private ILibroRepository libroRepository;
    private IAutorRepository autorRepository;

    public Principal(ILibroRepository libroRepository,IAutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while(opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo.
                    2 - Listar libros registrados.
                    3 - Listar autores registrados.
                    4 - Listar autores vivos en un determinado año.
                    5 - Listar libros por idioma.
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case (1):
                    buscarLibroPorTitulo();
                    break;
                case (2):
                    listarLibrosRegistrados();
                    break;
                case (3):
                    listarAutoresRegistrados();
                    break;
                case (4):
                    listarAutoresVivosEnUnAnio();
                    break;
                case (5):
                    listarLibrosPorIdioma();
                    break;
                case (0):
                    System.out.println("Gracias por utilizar Literalura");
                    break;
                default:
                    System.out.println("Opción invalida");

            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Escriba el nombre del libro a buscar");
        var tituloLibro = teclado.nextLine();

        var json = consumoAPI.obtenerDatos(URL_BASE + "search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isEmpty()) {
            System.out.println("Libro no encontrado");
            return;
        }

        DatosLibros datos = libroBuscado.get();
        DatosAutor datosAutor = datos.autor().get(0);

        Autor autor = autorRepository
                .findByNombreIgnoreCase(datosAutor.nombre())
                .orElseGet(() -> autorRepository.save(new Autor(datosAutor)));

        Libro libro = new Libro(datos, autor);

        libroRepository.save(libro);

        System.out.println("Libro guardado correctamente");
        System.out.println(libro);
    }


    private void listarLibrosRegistrados() {
        libroRepository.findAll().forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        autorRepository.buscarAutoresConLibros().
                forEach(System.out::println);
    }

    private void listarAutoresVivosEnUnAnio() {
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar");
        var anio = teclado.nextInt();
        teclado.nextLine();
        autorRepository.buscarAutoresVivosEnAnio(anio).forEach(System.out::println);
    }

    private void listarLibrosPorIdioma() {
        var menuIdioma = """
                Ingrese el idioma deseado para buscar el libro:
                es - Español
                en - Ingles
                fr - Frances
                pt - Portugues
                
                S - Salir
                """;

        System.out.println(menuIdioma);
        var opcionIdioma = teclado.nextLine().toLowerCase().trim();

        switch (opcionIdioma) {
            case ("es"):
                mostrarLibrosEn("es");
                break;
            case ("en"):
                mostrarLibrosEn("en");
                break;
            case ("fr"):
                mostrarLibrosEn("fr");
                break;
            case ("pt"):
                mostrarLibrosEn("pt");
                break;
            case ("s"):
                break;
            default:
                System.out.println("Operacion invalida");
                break;
        }
    }

    private void mostrarLibrosEn(String idioma) {
        libroRepository.findByIdioma(idioma).forEach(System.out::println);
    }
}
