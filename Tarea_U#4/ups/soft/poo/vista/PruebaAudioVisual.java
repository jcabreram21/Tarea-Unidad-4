package ups.soft.poo.vista;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import ups.soft.poo.controlador.ControladorAudiovisual;
import ups.soft.poo.controlador.GestorArchivosCsv;
import ups.soft.poo.modelo.Actor;
import ups.soft.poo.modelo.ContenidoAudiovisual;
import ups.soft.poo.modelo.Documental;
import ups.soft.poo.modelo.Investigador;
import ups.soft.poo.modelo.Pelicula;
import ups.soft.poo.modelo.Podcast;
import ups.soft.poo.modelo.SerieDeTV;
import ups.soft.poo.modelo.Temporada;
import ups.soft.poo.modelo.VideoMusical;

public class PruebaAudioVisual {

    private static final String RUTA_CSV = "contenidos.csv";

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        ArrayList<SerieDeTV> listaSeries = new ArrayList<>();
        ArrayList<Documental> listaDocumentales = new ArrayList<>();
        ArrayList<VideoMusical> listaVideosMusicales = new ArrayList<>();
        ArrayList<Podcast> listaPodcasts = new ArrayList<>();

        cargarDatosIniciales(
        listaPeliculas,
        listaSeries,
        listaDocumentales,
        listaVideosMusicales,
        listaPodcasts);

        ControladorAudiovisual controlador = new ControladorAudiovisual(
        listaPeliculas,
        listaSeries,
        listaDocumentales,
        listaVideosMusicales,
        listaPodcasts);

        ejecutarMenu(
        entrada,
        controlador,
        listaPeliculas,
        listaSeries,
        listaDocumentales,
        listaVideosMusicales,
        listaPodcasts);
    }

    private static void cargarDatosIniciales(
            List<Pelicula> listaPeliculas,
            List<SerieDeTV> listaSeries,
            List<Documental> listaDocumentales,
            List<VideoMusical> listaVideosMusicales,
            List<Podcast> listaPodcasts) {

        Pelicula peliculaThMa = new Pelicula("The Matrix", 136, "Ciencia Ficcion", "Warner Bros");
        Actor keanuReeves = new Actor("Keanu Reeves", 62, 1.86, "Canadiense");
        Actor carrieAnneMoss = new Actor("Carrie Anne Moss", 56, 1.71, "Canadiense");
        Actor laurenceFishburne = new Actor("Laurence Fishburne", 64, 1.84, "Estadounidense");
        peliculaThMa.agregarActores(keanuReeves, carrieAnneMoss, laurenceFishburne);
        listaPeliculas.add(peliculaThMa);

        Pelicula peliculaThDaKn = new Pelicula("The Dark Night", 152, "Accion", "Warner Bros");
        Actor christianBale = new Actor("Christian Bale", 52, 1.83, "Britanica");
        Actor cillianMurphy = new Actor("Cillian Murphy", 50, 1.75, "Irlandesa");
        Actor morganFreeman = new Actor("Morgan Freeman", 89, 1.88, "Estadounidense");
        peliculaThDaKn.agregarActores(christianBale, cillianMurphy, morganFreeman);
        listaPeliculas.add(peliculaThDaKn);

        SerieDeTV serieThBrBa = new SerieDeTV("The Breaking Bad", 540, "Drama", 5);
        serieThBrBa.agregarTemporada(new Temporada(1, 7));
        serieThBrBa.agregarTemporada(new Temporada(2, 13));
        serieThBrBa.agregarTemporada(new Temporada(3, 13));
        serieThBrBa.agregarTemporada(new Temporada(4, 13));
        serieThBrBa.agregarTemporada(new Temporada(5, 16));
        listaSeries.add(serieThBrBa);

        SerieDeTV serieStTh = new SerieDeTV("Stranger Things", 480, "Ciencia Ficción", 4);
        serieStTh.agregarTemporada(new Temporada(1, 8));
        serieStTh.agregarTemporada(new Temporada(2, 9));
        serieStTh.agregarTemporada(new Temporada(3, 8));
        serieStTh.agregarTemporada(new Temporada(4, 9));
        listaSeries.add(serieStTh);

        Investigador investigadorTrHa = new Investigador("Tristan Harris", 41, "Estadounidense");
        listaDocumentales.add(new Documental(
        "El dilema de las redes sociales",94,"Documental","Ética en la tecnología y redes sociales",
                investigadorTrHa));

        Investigador investigadorRiVe = new Investigador("Richard Vevers", 48, "Británico");
        listaDocumentales.add(new Documental(
        "En busca del coral",89,"Documental","Blanqueamiento de corales",
                investigadorRiVe));

        VideoMusical videoMRuRu = new VideoMusical("Rush Rush", 4, "Pop", "Paula Abdul");
        videoMRuRu.agregarActores(keanuReeves);
        listaVideosMusicales.add(videoMRuRu);

        VideoMusical videoMThWa = new VideoMusical("The Water", 15, "Indie", "Feist");
        videoMThWa.agregarActores(cillianMurphy);
        listaVideosMusicales.add(videoMThWa);

        listaPodcasts.add(new Podcast(
        "La ética de la tecnología y la atención humana",45,"Tecnología",1,1,
                investigadorTrHa));

        listaPodcasts.add(new Podcast(
        "La conservación de los océanos y el impacto visual", 50, "Ciencia y Ambiente", 1, 2,
                investigadorRiVe));
    }

    private static void ejecutarMenu(
            Scanner entrada,
            ControladorAudiovisual controlador,
            ArrayList<Pelicula> listaPeliculas,
            ArrayList<SerieDeTV> listaSeries,
            ArrayList<Documental> listaDocumentales,
            ArrayList<VideoMusical> listaVideosMusicales,
            ArrayList<Podcast> listaPodcasts) {

        int eleccion;
        	do {
        		mostrarMenuPrincipal();
        		eleccion = leerOpcionMenu(entrada);
        		procesarOpcion(
        				eleccion,
        				entrada,
        				controlador,
        				listaPeliculas,
        				listaSeries,
        				listaDocumentales,
        				listaVideosMusicales,
        				listaPodcasts);
        } while (eleccion != 9);

        System.out.println("Adios, vuelve pronto!");
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n======== GESTION DE CONTENIDO AUDIOVISUAL ========");
        System.out.println("Hola, puedes elegir entre Peliculas, Series, Documentales, Videos Musicales o Podcasts.\n");
        System.out.println("Menu de eleccion:\n1. Peliculas.\n2. Series.\n3. Documentales.\n4. Videos Musicales.\n5. Podcasts.\n6. Agregar contenido.\n7. Guardar contenido.\n8. Cargar contenido.\n9. Salir.");
    }

    private static int leerOpcionMenu(Scanner entrada) {
        int eleccion = validacionIngresos(entrada);
        while (eleccion < 1 || eleccion > 9) {
            System.out.println("Ingrese un valor correcto!");
            eleccion = validacionIngresos(entrada);
        }
        return eleccion;
    }

    private static void procesarOpcion(
            int eleccion,
            Scanner entrada,
            ControladorAudiovisual controlador,
            ArrayList<Pelicula> listaPeliculas,
            ArrayList<SerieDeTV> listaSeries,
            ArrayList<Documental> listaDocumentales,
            ArrayList<VideoMusical> listaVideosMusicales,
            ArrayList<Podcast> listaPodcasts) {

        GestorArchivosCsv gestorArchivos = new GestorArchivosCsv();

        switch (eleccion) {
            case 1:
                seleccionarYMostrar(entrada, listaPeliculas, "Lista de Peliculas:", "Elige el número de la película que deseas ver:");
         break;
            case 2:
                seleccionarYMostrar(entrada, listaSeries, "Lista de Series:", "Elige el número de la serie que deseas ver:");
         break;
            case 3:
                seleccionarYMostrar(entrada, listaDocumentales, "Lista de Documentales:", "Elige el número del Documental que deseas ver:");
         break;
            case 4:
                seleccionarYMostrar(entrada, listaVideosMusicales, "Lista de Videos Musicales:", "Elige el número del video musical que deseas ver:");
         break;
            case 5:
                seleccionarYMostrar(entrada, listaPodcasts, "Lista de Podcasts:", "Elige el número del Podcast que deseas ver:");
         break;
            case 6:
                agregarContenido(entrada, controlador, listaSeries);
         break;
            case 7:
                gestorArchivos.guardarContenidosDesdeListas(
                        RUTA_CSV, listaPeliculas, listaSeries, listaDocumentales, listaVideosMusicales, listaPodcasts);
         break;
            case 8:
                gestorArchivos.cargarContenidosEnListas(
                        RUTA_CSV, listaPeliculas, listaSeries, listaDocumentales, listaVideosMusicales, listaPodcasts);
         break;
            case 9:
         break;
        }
    }

    private static <T extends ContenidoAudiovisual> void seleccionarYMostrar(
            Scanner entrada,
            List<T> elementos,
            String encabezadoLista,
            String mensajeSeleccion) {

        if (elementos == null || elementos.isEmpty()) {
            System.out.println("\nNo hay elementos disponibles en esta categoría.");
            return;
        }

        System.out.println("\n" + encabezadoLista);
        for (int i = 0; i < elementos.size(); i++) {
            System.out.println((i + 1) + ". " + elementos.get(i).getTitulo());
        }

        System.out.print(mensajeSeleccion + "\n ");
        int seleccion = validacionIngresos(entrada);
        while (seleccion < 1 || seleccion > elementos.size()) {
            System.out.println("Error! Valor ingresado erroneo, elige un número de la lista:");
            seleccion = validacionIngresos(entrada);
        }

        elementos.get(seleccion - 1).mostrarDetalles();
    }

    private static void agregarContenido(
        Scanner entrada,
        ControladorAudiovisual controlador,
        ArrayList<SerieDeTV> listaSeries) {

        System.out.println("\n¿Qué tipo de contenido deseas agregar?");
        System.out.println("1. Película");
        System.out.println("2. Serie de TV");
        System.out.println("3. Documental");
        System.out.println("4. Video Musical");
        System.out.println("5. Podcast");
        int tipo = validacionIngresos(entrada);

        switch (tipo) {
            case 1:
                solicitarPelicula(entrada, controlador);
          break;
            case 2:
                solicitarSerie(entrada, controlador, listaSeries);
          break;
            case 3:
                solicitarDocumental(entrada, controlador);
          break;
            case 4:
                solicitarVideoMusical(entrada, controlador);
          break;
            case 5:
                solicitarPodcast(entrada, controlador);
          break;
            default:
                System.out.println("Tipo no válido.");
          break;
        }
    }

    private static void solicitarPelicula(Scanner entrada, ControladorAudiovisual controlador) {
        String titulo = leerTexto(entrada, "Título: ");
        int duracion = leerEnteroPositivo(entrada, "Duración en minutos: ");
        String genero = leerTexto(entrada, "Género: ");
        String estudio = leerTexto(entrada, "Estudio: ");
        List<Actor> actores = recolectarActores(entrada, controlador);

        boolean agregado = controlador.agregarPelicula(titulo, duracion, genero, estudio, actores);
        mostrarResultado(agregado, "Película agregada correctamente.");
    }

    private static void solicitarSerie(
        Scanner entrada,
        ControladorAudiovisual controlador,
        ArrayList<SerieDeTV> listaSeries) {

        String titulo = leerTexto(entrada, "Título: ");
        int duracion = leerEnteroPositivo(entrada, "Duración en minutos: ");
        String genero = leerTexto(entrada, "Género: ");
        int temporadas = leerEnteroPositivo(entrada, "Número de temporadas: ");

        boolean agregado = controlador.agregarSerie(titulo, duracion, genero, temporadas);
        if (agregado && !listaSeries.isEmpty()) {
            SerieDeTV serieCreada = listaSeries.get(listaSeries.size() - 1);
            agregarTemporadasDesdeConsola(entrada, serieCreada, temporadas);
        }

            mostrarResultado(agregado, "Serie agregada correctamente.");
    }

    private static void solicitarDocumental(Scanner entrada, ControladorAudiovisual controlador) {
        String titulo = leerTexto(entrada, "Título: ");
        int duracion = leerEnteroPositivo(entrada, "Duración en minutos: ");
        String genero = leerTexto(entrada, "Género: ");
        String tema = leerTexto(entrada, "Tema principal: ");
        Investigador investigador = solicitarInvestigadorOpcional(
        entrada,
        controlador,
        "¿Deseas agregar un investigador principal? (s/n): ");

        boolean agregado = controlador.agregarDocumental(titulo, duracion, genero, tema, investigador);
        mostrarResultado(agregado, "Documental agregado correctamente.");
    }

    private static void solicitarVideoMusical(Scanner entrada, ControladorAudiovisual controlador) {
        String titulo = leerTexto(entrada, "Título: ");
        int duracion = leerEnteroPositivo(entrada, "Duración en minutos: ");
        String genero = leerTexto(entrada, "Género: ");
        String artista = leerTexto(entrada, "Artista: ");
        List<Actor> actores = recolectarActores(entrada, controlador);

        boolean agregado = controlador.agregarVideoMusical(titulo, duracion, genero, artista, actores);
        mostrarResultado(agregado, "Video musical agregado correctamente.");
    }

    private static void solicitarPodcast(Scanner entrada, ControladorAudiovisual controlador) {
        String titulo = leerTexto(entrada, "Título: ");
        int duracion = leerEnteroPositivo(entrada, "Duración en minutos: ");
        String genero = leerTexto(entrada, "Género: ");
        int temporada = leerEnteroPositivo(entrada, "Número de temporada: ");
        int episodio = leerEnteroPositivo(entrada, "Número de episodio: ");
        Investigador invitado = solicitarInvestigadorOpcional(
        entrada,
        controlador,
        "¿Deseas agregar un investigador invitado? (s/n): ");

        boolean agregado = controlador.agregarPodcast(titulo, duracion, genero, temporada, episodio, invitado);
        mostrarResultado(agregado, "Podcast agregado correctamente.");
    }

    private static List<Actor> recolectarActores(Scanner entrada, ControladorAudiovisual controlador) {
        List<Actor> actores = new ArrayList<>();
        while (confirmarSiNo(entrada, "¿Deseas agregar un actor? (s/n): ")) {
            Actor actor = crearActorDesdeConsola(entrada, controlador);
            if (actor != null) {
                actores.add(actor);
            } else {
                System.out.println("Datos de actor no válidos.");
              }
        }
        return actores;
    }

    private static Actor crearActorDesdeConsola(Scanner entrada, ControladorAudiovisual controlador) {
        String nombre = leerTexto(entrada, "Nombre del actor: ");
        int edad = leerEnteroPositivo(entrada, "Edad: ");
        double altura = leerDecimalPositivo(entrada, "Altura en metros (ej. 1.75): ");
        String nacionalidad = leerTexto(entrada, "Nacionalidad: ");
        return controlador.crearActor(nombre, edad, altura, nacionalidad);
    }

    private static Investigador solicitarInvestigadorOpcional(
            Scanner entrada,
            ControladorAudiovisual controlador,
            String pregunta) {

        if (!confirmarSiNo(entrada, pregunta)) {
            return null;
        }
        String nombre = leerTexto(entrada, "Nombre del investigador: ");
        int edad = leerEnteroPositivo(entrada, "Edad: ");
        String nacionalidad = leerTexto(entrada, "Nacionalidad: ");
        Investigador investigador = controlador.crearInvestigador(nombre, edad, nacionalidad);
        	if (investigador == null) {
        		System.out.println("Datos no válidos; se registrará sin investigador.");
        }
        		return investigador;
    }

    private static boolean confirmarSiNo(Scanner entrada, String mensaje) {
        while (true) {
            String respuesta = leerTexto(entrada, mensaje).trim().toLowerCase();
            if (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) {
                return true;
            }
            	if (respuesta.equals("n") || respuesta.equals("no")) {
            		return false;
                }
            		System.out.println("Error! Responde solo 's'/'si' o 'n'/'no'.");
        }
    }

    private static String leerTexto(Scanner entrada, String mensaje) {
        System.out.print(mensaje);
        String texto = entrada.nextLine();
        if (texto.trim().isEmpty()) {
            texto = entrada.nextLine();
        }
            return texto.trim();
    }

    private static int leerEnteroPositivo(Scanner entrada, String mensaje) {
        int valor = 0;
        while (valor <= 0) {
            System.out.print(mensaje);
            valor = validacionIngresos(entrada, "Error! Debes ingresar un número entero válido:");
            if (valor <= 0) {
                System.out.println("Ingresa un número mayor que 0.");
            }
        }
        return valor;
    }

    private static double leerDecimalPositivo(Scanner entrada, String mensaje) {
        double valor = 0;
        while (valor <= 0) {
            System.out.print(mensaje);
            String token = entrada.next().replace(',', '.');
            try {
                valor = Double.parseDouble(token);
                if (valor <= 0) {
                    System.out.println("Ingresa un número mayor que 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error! Ingresa un decimal válido:");
              }
        }
        return valor;
    }

    private static void mostrarResultado(boolean agregado, String mensajeExito) {
        if (agregado) {
            System.out.println(mensajeExito);
        } else {
            System.out.println("No se pudo agregar: revisa los datos ingresados.");
          }
    }

    public static int validacionIngresos(Scanner scanner) {
        return validacionIngresos(scanner, "Error! Debes elegir una opcion valida:");
    }

    public static int validacionIngresos(Scanner scanner, String mensajeError) {
        int numero = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                numero = scanner.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println(mensajeError);
                scanner.next();
              }
        }
        return numero;
    }

    private static void agregarTemporadasDesdeConsola(Scanner entrada, SerieDeTV serie, int totalTemporadas) {
        if (serie == null || totalTemporadas <= 0) {
            return;
        }

        boolean deseaDetalle = confirmarSiNo(entrada, "¿Deseas agregar los capítulos de cada temporada? (s/n): ");
        	if (!deseaDetalle) {
        		return;
        }

        	for (int numeroTemp = 1; numeroTemp <= totalTemporadas; numeroTemp++) {
        		int cantidadCapitulos = leerEnteroPositivo(
                    entrada,
                    "Cantidad de capítulos de la temporada " + numeroTemp + ": ");
            serie.agregarTemporada(new Temporada(numeroTemp, cantidadCapitulos));
        }
    }
}