package ups.soft.poo.modelo;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

// Pruebas para revisar si es q las clases guarden y cambien bien sus datos
class Documental_pelicula_podcast{
 @Test
    void probarPeliculaYActores() {
        Pelicula peli = new Pelicula("Matrix", 136, "Ciencia Ficcion", "Warner");
        Actor a1 = new Actor("Keanu Reeves", 62, 1.86, "Canadiense");
        peli.agregarActores(a1, null);

        assertEquals("Matrix", peli.getTitulo());
        assertEquals(136, peli.getDuracionEnMinutos());
        assertEquals("Warner", peli.getEstudio());
        assertEquals(1, peli.getActores().size());
        assertTrue(peli.construirFilaCsv().startsWith("Pelicula,Matrix,136,Ciencia Ficcion,Warner,"));
    }

    @Test
    void probarDocumental() {
        Investigador inv = new Investigador("David Attenborough", 98, "Britanica");
        Documental doc = new Documental("Planeta Tierra", 90, "Naturaleza", "Ecosistemas", inv);

        assertEquals("Ecosistemas", doc.getTema());
        assertEquals(inv, doc.getInvestigador());
        assertEquals("Documental,Planeta Tierra,90,Naturaleza,Ecosistemas,David Attenborough|98|Britanica",
                doc.construirFilaCsv());
    }

    @Test
    void probarPodcast() {
        Investigador inv = new Investigador("Ana Perez", 34, "Ecuatoriana");
        Podcast pod = new Podcast("Charla Tech", 45, "Tecnologia", 2, 5, inv);

        assertEquals(2, pod.getNumTemporada());
        assertEquals(5, pod.getNumEpisodio());
        assertEquals(inv, pod.getInvitado());
        assertEquals("Podcast,Charla Tech,45,Tecnologia,2,5,Ana Perez|34|Ecuatoriana",
                pod.construirFilaCsv());
    }
}