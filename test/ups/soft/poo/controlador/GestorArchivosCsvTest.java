package ups.soft.poo.controlador;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import ups.soft.poo.modelo.Actor;
import ups.soft.poo.modelo.Documental;
import ups.soft.poo.modelo.Investigador;
import ups.soft.poo.modelo.Pelicula;
import ups.soft.poo.modelo.Podcast;
import ups.soft.poo.modelo.SerieDeTV;
import ups.soft.poo.modelo.VideoMusical;

// Pruebas para revisar guardar y cargar csv con el gestor
class GestorArchivosCsvTest {

    @TempDir
    Path carpetaTemporal;

    private GestorArchivosCsv gestor;
    private Path archivoCsv;

    @BeforeEach
    void preparar() {
        gestor = new GestorArchivosCsv();
        archivoCsv = carpetaTemporal.resolve("contenidos_prueba.csv");
    }

    @Test
    void guardarYCargarContenidosDesdeListas() {
        List<Pelicula> peliculas = new ArrayList<>();
        Pelicula peli = new Pelicula("Inception", 148, "Ciencia Ficcion", "Warner");
        peli.agregarActores(new Actor("Leonardo DiCaprio", 50, 1.83, "Estadounidense"));
        peliculas.add(peli);

        List<SerieDeTV> series = new ArrayList<>();
        series.add(new SerieDeTV("Dark", 60, "Misterio", 3));

        List<Documental> documentales = new ArrayList<>();
        documentales.add(new Documental("Tierra", 90, "Naturaleza", "Planeta",
                new Investigador("David", 98, "Britanica")));

        List<VideoMusical> videos = new ArrayList<>();
        videos.add(new VideoMusical("Clip", 4, "Pop", "Artista"));

        List<Podcast> podcasts = new ArrayList<>();
        podcasts.add(new Podcast("Episodio 1", 40, "Tech", 1, 2, null));

        gestor.guardarContenidosDesdeListas(
                archivoCsv.toString(), peliculas, series, documentales, videos, podcasts);

        // Pongo un elemento viejo para comprobar que cargarContenidosEnListas limpie y cargue bien
        List<Pelicula> pelisCargadas = new ArrayList<>();
        pelisCargadas.add(new Pelicula("Vieja", 80, "Drama", "Estudio"));
        List<SerieDeTV> seriesCargadas = new ArrayList<>();
        List<Documental> docsCargados = new ArrayList<>();
        List<VideoMusical> videosCargados = new ArrayList<>();
        List<Podcast> podcastsCargados = new ArrayList<>();

        gestor.cargarContenidosEnListas(
                archivoCsv.toString(), pelisCargadas, seriesCargadas, docsCargados, videosCargados, podcastsCargados);

        assertEquals(1, pelisCargadas.size());
        assertEquals("Inception", pelisCargadas.get(0).getTitulo());
        assertEquals(1, seriesCargadas.size());
        assertEquals(1, docsCargados.size());
        assertEquals(1, videosCargados.size());
        assertEquals(1, podcastsCargados.size());
    }

    @Test
    void cargarIgnoraLineasVaciasYTiposDesconocidos() throws IOException {
        String contenido = String.join(System.lineSeparator(),
                "",
                "TipoRaro,Algo,10,Genero",
                "Pelicula,Matrix,136,Accion,Warner,",
                "   "
        );
        Files.writeString(archivoCsv, contenido);

        var cargados = gestor.cargarContenidosCsv(archivoCsv.toString());

        assertEquals(1, cargados.size());
        assertTrue(cargados.get(0) instanceof Pelicula);
    }

    @Test
    void lanzaErrorSiRutaOListaSonInvalidas() {
        assertThrows(IllegalArgumentException.class,
                () -> gestor.guardarContenidosCsv("", new ArrayList<>()));
        assertThrows(IllegalArgumentException.class,
                () -> gestor.guardarContenidosCsv(archivoCsv.toString(), null));
        assertThrows(IllegalArgumentException.class,
                () -> gestor.cargarContenidosCsv("   "));
    }

    @Test
    void lanzaErrorSiArchivoNoExisteAlCargar() {
        Path inexistente = carpetaTemporal.resolve("no_existe.csv");
        assertThrows(RuntimeException.class,
                () -> gestor.cargarContenidosCsv(inexistente.toString()));
    }
}