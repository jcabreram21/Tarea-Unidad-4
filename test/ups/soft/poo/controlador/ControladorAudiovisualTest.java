package ups.soft.poo.controlador;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ups.soft.poo.modelo.Actor;
import ups.soft.poo.modelo.Documental;
import ups.soft.poo.modelo.Investigador;
import ups.soft.poo.modelo.Pelicula;
import ups.soft.poo.modelo.Podcast;
import ups.soft.poo.modelo.VideoMusical;

// Pruebas para revisar que el controlador valide bien y no guarde datos dañados
class ControladorAudiovisualTest {

    private ControladorAudiovisual controlador;

    @BeforeEach
    void preparar() {
        controlador = new ControladorAudiovisual();
    }

    @Test
    void agregarPeliculaValidaYQuitarEspacios() {
        boolean guardada = controlador.agregarPelicula("  The Matrix  ", 136, "  Ciencia Ficcion ", " Warner Bros ");

        assertTrue(guardada);
        assertEquals(1, controlador.getPeliculas().size());
        assertEquals("The Matrix", controlador.getPeliculas().get(0).getTitulo());
    }

    @Test
    void noDebeGuardarPeliculaConDatosInvalidos() {
        assertFalse(controlador.agregarPelicula("", 120, "Accion", "Estudio"));
        assertFalse(controlador.agregarPelicula("   ", 120, "Accion", "Estudio"));
        assertFalse(controlador.agregarPelicula("Titulo", 0, "Accion", "Estudio"));
        assertFalse(controlador.agregarPelicula("Titulo", -15, "Accion", "Estudio"));
        assertFalse(controlador.agregarPelicula("Titulo", 100, null, "Estudio"));
        assertFalse(controlador.agregarPelicula("Titulo", 100, "Accion", ""));
        assertEquals(0, controlador.getPeliculas().size());
    }

    @Test
    void agregarPeliculaConActoresIgnoraLosNulos() {
        List<Actor> actores = new ArrayList<>();
        actores.add(new Actor("Keanu Reeves", 62, 1.86, "Canadiense"));
        actores.add(null);

        boolean ok = controlador.agregarPelicula("Matrix", 136, "Ciencia Ficcion", "Warner", actores);

        assertTrue(ok);
        assertEquals(1, controlador.getPeliculas().size());
        assertEquals(1, controlador.getPeliculas().get(0).getActores().size());
    }

    @Test
    void validarCreacionDeActorYLimites() {
        Actor valido = controlador.crearActor("Ana", 1, 1.50, "Ecuatoriana");
        assertNotNull(valido);

        // Edad o altura en cero o menor no deben pasar
        assertNull(controlador.crearActor("Ana", 0, 1.60, "Ecuatoriana"));
        assertNull(controlador.crearActor("Ana", 25, 0.0, "Ecuatoriana"));
        assertNull(controlador.crearActor("   ", 25, 1.60, "Ecuatoriana"));
    }

    @Test
    void probarSeriesDocumentalesYPodcasts() {
        Investigador inv = controlador.crearInvestigador("Carlos", 35, "Ecuatoriano");
        assertNotNull(inv);

        assertTrue(controlador.agregarDocumental("Oceano", 60, "Ciencia", "Mares", inv));
        assertFalse(controlador.agregarDocumental("Oceano", 60, "Ciencia", "   ", inv));

        assertTrue(controlador.agregarSerie("Serie prueba", 45, "Drama", 1));
        assertFalse(controlador.agregarSerie("Serie mala", 45, "Drama", 0));

        Documental doc = controlador.getDocumentales().get(0);
        assertFalse(controlador.asignarInvestigadorADocumental(null, "Ana", 30, "Peruana"));
        assertTrue(controlador.asignarInvestigadorADocumental(doc, "Ana", 30, "Peruana"));

        assertTrue(controlador.agregarPodcast("Charla", 30, "Tech", 1, 1, null));
        Podcast pod = controlador.getPodcasts().get(0);
        assertTrue(controlador.asignarInvitadoAPodcast(pod, "Luis", 40, "Chilena"));
    }

    @Test
    void probarVideoMusicalYAsignarActor() {
        assertTrue(controlador.agregarVideoMusical("Clip", 4, "Pop", "Artista"));
        assertFalse(controlador.agregarVideoMusical("Clip", 4, "Pop", ""));

        VideoMusical video = controlador.getVideosMusicales().get(0);
        assertTrue(controlador.agregarActorAVideoMusical(video, "Actor 1", 28, 1.75, "Colombiana"));
        assertFalse(controlador.agregarActorAVideoMusical(null, "Actor 1", 28, 1.75, "Colombiana"));
    }

    @Test
    void listasDevueltasNoSePuedenModificarDesdeAfuera() {
        controlador.agregarPelicula("Peli", 90, "Drama", "Indie");
        List<Pelicula> listaExterna = controlador.getPeliculas();

        assertThrows(UnsupportedOperationException.class, listaExterna::clear);
    }
}