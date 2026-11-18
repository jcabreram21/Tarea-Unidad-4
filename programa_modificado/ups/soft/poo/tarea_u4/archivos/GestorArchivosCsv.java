package ups.soft.poo.tarea_u4.archivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ups.soft.poo.tarea_u2.Actor;
import ups.soft.poo.tarea_u2.ContenidoAudiovisual;
import ups.soft.poo.tarea_u2.Documental;
import ups.soft.poo.tarea_u2.Investigador;
import ups.soft.poo.tarea_u2.Pelicula;
import ups.soft.poo.tarea_u2.Podcast;
import ups.soft.poo.tarea_u2.SerieDeTV;
import ups.soft.poo.tarea_u2.Temporada;
import ups.soft.poo.tarea_u2.VideoMusical;

public class GestorArchivosCsv {

    private static final String DELIMITADOR_CSV = ",";

    public void guardarContenidosDesdeListas(
            String rutaArchivoCsv,
            List<Pelicula> listaPeliculas,
            List<SerieDeTV> listaSeries,
            List<Documental> listaDocumentales,
            List<VideoMusical> listaVideosMusicales,
            List<Podcast> listaPodcasts) {

        List<ContenidoAudiovisual> todosLosContenidos = new ArrayList<>();
        if (listaPeliculas != null) todosLosContenidos.addAll(listaPeliculas);
        if (listaSeries != null) todosLosContenidos.addAll(listaSeries);
        if (listaDocumentales != null) todosLosContenidos.addAll(listaDocumentales);
        if (listaVideosMusicales != null) todosLosContenidos.addAll(listaVideosMusicales);
        if (listaPodcasts != null) todosLosContenidos.addAll(listaPodcasts);

        guardarContenidosCsv(rutaArchivoCsv, todosLosContenidos);
        System.out.println("Guardado en " + rutaArchivoCsv);
    }

    public void cargarContenidosEnListas(
            String rutaArchivoCsv,
            List<Pelicula> listaPeliculas,
            List<SerieDeTV> listaSeries,
            List<Documental> listaDocumentales,
            List<VideoMusical> listaVideosMusicales,
            List<Podcast> listaPodcasts) {

        List<ContenidoAudiovisual> contenidosCargados = cargarContenidosCsv(rutaArchivoCsv);
        limpiarListas(listaPeliculas, listaSeries, listaDocumentales, listaVideosMusicales, listaPodcasts);

        for (ContenidoAudiovisual item : contenidosCargados) {
            if (item instanceof Pelicula && listaPeliculas != null) {
                listaPeliculas.add((Pelicula) item);
            } else if (item instanceof SerieDeTV && listaSeries != null) {
                listaSeries.add((SerieDeTV) item);
            } else if (item instanceof Documental && listaDocumentales != null) {
                listaDocumentales.add((Documental) item);
            } else if (item instanceof VideoMusical && listaVideosMusicales != null) {
                listaVideosMusicales.add((VideoMusical) item);
            } else if (item instanceof Podcast && listaPodcasts != null) {
                listaPodcasts.add((Podcast) item);
            }
        }

        System.out.println("Cargados " + contenidosCargados.size() + " elementos desde " + rutaArchivoCsv);
    }

    public void guardarContenidosCsv(String rutaArchivoCsv, List<ContenidoAudiovisual> listaContenidosAudiovisuales) {
        if (rutaArchivoCsv == null || rutaArchivoCsv.trim().isEmpty() || listaContenidosAudiovisuales == null) {
            throw new IllegalArgumentException("La ruta del archivo CSV y la lista de contenidos no pueden ser nulas o vacías.");
        }

        try (BufferedWriter escritorCsv = new BufferedWriter(new FileWriter(rutaArchivoCsv))) {
            for (ContenidoAudiovisual contenidoItem : listaContenidosAudiovisuales) {
                if (contenidoItem != null) {
                    escritorCsv.write(contenidoItem.construirFilaCsv());
                    escritorCsv.newLine();
                }
            }
        } catch (IOException excepcionEscritura) {
            throw new RuntimeException("Error al escribir los contenidos audiovisuales en el archivo CSV: " + rutaArchivoCsv, excepcionEscritura);
        }
    }

    public List<ContenidoAudiovisual> cargarContenidosCsv(String rutaArchivoCsv) {
        if (rutaArchivoCsv == null || rutaArchivoCsv.trim().isEmpty()) {
            throw new IllegalArgumentException("La ruta del archivo CSV no puede ser nula o vacía.");
        }

        List<ContenidoAudiovisual> contenidosReconstruidos = new ArrayList<>();

        try (BufferedReader lectorCsv = new BufferedReader(new FileReader(rutaArchivoCsv))) {
            String filaLeidaCsv;
            while ((filaLeidaCsv = lectorCsv.readLine()) != null) {
                if (!filaLeidaCsv.trim().isEmpty()) {
                    ContenidoAudiovisual contenidoInstanciado = reconstruirContenidoCsv(filaLeidaCsv);
                    if (contenidoInstanciado != null) {
                        contenidosReconstruidos.add(contenidoInstanciado);
                    }
                }
            }
        } catch (IOException excepcionLectura) {
            throw new RuntimeException("Error al leer los contenidos audiovisuales desde el archivo CSV: " + rutaArchivoCsv, excepcionLectura);
        }

        return contenidosReconstruidos;
    }

    private void limpiarListas(
            List<Pelicula> listaPeliculas,
            List<SerieDeTV> listaSeries,
            List<Documental> listaDocumentales,
            List<VideoMusical> listaVideosMusicales,
            List<Podcast> listaPodcasts) {
        if (listaPeliculas != null) listaPeliculas.clear();
        if (listaSeries != null) listaSeries.clear();
        if (listaDocumentales != null) listaDocumentales.clear();
        if (listaVideosMusicales != null) listaVideosMusicales.clear();
        if (listaPodcasts != null) listaPodcasts.clear();
    }

    private ContenidoAudiovisual reconstruirContenidoCsv(String filaLeidaCsv) {
        String[] cols = filaLeidaCsv.split(DELIMITADOR_CSV, -1);
        String tipo = cols[0].trim();

        switch (tipo) {
            case "Documental":
                return leerDocumental(cols);
            case "Pelicula":
                return leerPelicula(cols);
            case "Podcast":
                return leerPodcast(cols);
            case "SerieDeTV":
                return leerSerie(cols);
            case "VideoMusical":
                return leerVideoMusical(cols);
            default:
                return null;
        }
    }

    private Documental leerDocumental(String[] cols) {
        String titulo = columna(cols, 1);
        int duracion = leerEnteroSeguro(columna(cols, 2));
        String genero = columna(cols, 3);
        String tema = columna(cols, 4);
        Investigador investigador = leerInvestigador(columna(cols, 5));
        return new Documental(titulo, duracion, genero, tema, investigador);
    }

    private Pelicula leerPelicula(String[] cols) {
        String titulo = columna(cols, 1);
        int duracion = leerEnteroSeguro(columna(cols, 2));
        String genero = columna(cols, 3);
        String estudio = columna(cols, 4);
        Pelicula pelicula = new Pelicula(titulo, duracion, genero, estudio);
        agregarActoresDesdeCsv(pelicula, columna(cols, 5));
        return pelicula;
    }

    private Podcast leerPodcast(String[] cols) {
        String titulo = columna(cols, 1);
        int duracion = leerEnteroSeguro(columna(cols, 2));
        String genero = columna(cols, 3);
        int temporada = leerEnteroSeguro(columna(cols, 4));
        int episodio = leerEnteroSeguro(columna(cols, 5));
        Investigador invitado = leerInvestigador(columna(cols, 6));
        return new Podcast(titulo, duracion, genero, temporada, episodio, invitado);
    }

    private SerieDeTV leerSerie(String[] cols) {
        String titulo = columna(cols, 1);
        int duracion = leerEnteroSeguro(columna(cols, 2));
        String genero = columna(cols, 3);
        int totalTemporadas = leerEnteroSeguro(columna(cols, 4));
        SerieDeTV serie = new SerieDeTV(titulo, duracion, genero, totalTemporadas);
        agregarTemporadasDesdeCsv(serie, columna(cols, 5));
        return serie;
    }

    private VideoMusical leerVideoMusical(String[] cols) {
        String titulo = columna(cols, 1);
        int duracion = leerEnteroSeguro(columna(cols, 2));
        String genero = columna(cols, 3);
        String artista = columna(cols, 4);
        String fecha = columna(cols, 5);
        VideoMusical video = new VideoMusical(titulo, duracion, genero, artista);
        video.setFechaLanzamiento(fecha);
        agregarActoresDesdeCsv(video, columna(cols, 6));
        return video;
    }

    private Investigador leerInvestigador(String datosCsv) {
        if (datosCsv == null || datosCsv.trim().isEmpty()) {
            return null;
        }
        String[] partes = datosCsv.split("\\|", -1);
        String nombre = partes.length > 0 ? partes[0].trim() : "";
        if (nombre.isEmpty()) {
            return null;
        }
        int edad = partes.length > 1 ? leerEnteroSeguro(partes[1]) : 0;
        String nacionalidad = partes.length > 2 ? partes[2].trim() : "";
        return new Investigador(nombre, edad, nacionalidad);
    }

    private void agregarActoresDesdeCsv(Pelicula pelicula, String actoresCsv) {
        for (Actor actor : leerActores(actoresCsv)) {
            pelicula.agregarActores(actor);
        }
    }

    private void agregarActoresDesdeCsv(VideoMusical video, String actoresCsv) {
        for (Actor actor : leerActores(actoresCsv)) {
            video.agregarActores(actor);
        }
    }

    private List<Actor> leerActores(String actoresCsv) {
        List<Actor> actores = new ArrayList<>();
        if (actoresCsv == null || actoresCsv.trim().isEmpty()) {
            return actores;
        }
        for (String bloqueActor : actoresCsv.split(";")) {
            String limpio = bloqueActor.trim();
            if (limpio.isEmpty()) {
                continue;
            }
            String[] partes = limpio.split("\\|", -1);
            String nombre = partes.length > 0 ? partes[0].trim() : "";
            if (nombre.isEmpty()) {
                continue;
            }
            int edad = partes.length > 1 ? leerEnteroSeguro(partes[1]) : 0;
            double altura = partes.length > 2 ? leerDecimalSeguro(partes[2]) : 0.0;
            String nacionalidad = partes.length > 3 ? partes[3].trim() : "";
            actores.add(new Actor(nombre, edad, altura, nacionalidad));
        }
        return actores;
    }

    private void agregarTemporadasDesdeCsv(SerieDeTV serie, String temporadasCsv) {
        if (temporadasCsv == null || temporadasCsv.trim().isEmpty()) {
            return;
        }
        for (String bloque : temporadasCsv.split(";")) {
            String limpio = bloque.trim().replace("ep", "");
            if (!limpio.isEmpty() && limpio.contains("-")) {
                String[] partes = limpio.split("-");
                int numTemp = leerEnteroSeguro(partes[0].replaceFirst("^[Tt]", "").trim());
                int numCap = leerEnteroSeguro(partes[1].trim());
                if (numTemp > 0 && numCap > 0) {
                    serie.agregarTemporada(new Temporada(numTemp, numCap));
                }
            }
        }
    }

    private String columna(String[] cols, int indice) {
        return (indice >= 0 && indice < cols.length) ? cols[indice].trim() : "";
    }

    private int leerEnteroSeguro(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double leerDecimalSeguro(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(texto.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}