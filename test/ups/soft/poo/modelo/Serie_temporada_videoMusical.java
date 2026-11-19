package ups.soft.poo.modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

// Pruebas para SerieDeTV, Temporada y VideoMusical
class Serie_temporada_videoMusical {

    @Test
    void probarTemporada() {
        Temporada temp = new Temporada(1, 10);

        assertEquals(1, temp.getNumTemporada());
        assertEquals(10, temp.getNumCapitulos());

        temp.setNumTemporada(2);
        temp.setNumCapitulos(12);

        assertEquals(2, temp.getNumTemporada());
        assertEquals(12, temp.getNumCapitulos());
    }

    @Test
    void probarSerieDeTV() {
        SerieDeTV serie = new SerieDeTV("Dark", 60, "Misterio", 3);
        Temporada t1 = new Temporada(1, 8);
        Temporada t2 = new Temporada(2, 8);

        serie.agregarTemporada(t1);
        serie.agregarTemporada(null);
        serie.agregarTemporada(t2);

        assertEquals("Dark", serie.getTitulo());
        assertEquals(60, serie.getDuracionEnMinutos());
        assertEquals("Misterio", serie.getGenero());
        assertEquals(3, serie.getTotalTemporadas());
        assertEquals(2, serie.getTemporadas().size());
        assertEquals("SerieDeTV,Dark,60,Misterio,2,1-8;2-8", serie.construirFilaCsv());

        serie.setTotalTemporadas(4);
        ArrayList<Temporada> listaNueva = new ArrayList<>();
        listaNueva.add(new Temporada(1, 10));
        serie.setTemporadas(listaNueva);

        assertEquals(4, serie.getTotalTemporadas());
        assertEquals("SerieDeTV,Dark,60,Misterio,1,1-10", serie.construirFilaCsv());
    }

    @Test
    void probarVideoMusical() {
        VideoMusical video = new VideoMusical("Cry", 4, "Ambient Pop", "Cigarettes After Sex");
        video.setFechaLanzamiento("2019-10-25");

        Actor actor = new Actor("Modelo Principal", 27, 1.75, "Estadounidense");
        video.agregarActores(actor, null);

        assertEquals("Cry", video.getTitulo());
        assertEquals(4, video.getDuracionEnMinutos());
        assertEquals("Ambient Pop", video.getGenero());
        assertEquals("Cigarettes After Sex", video.getArtista());
        assertEquals("2019-10-25", video.getFechaLanzamiento());
        assertEquals(1, video.getActoresVidMus().size());
        assertEquals(
                "VideoMusical,Cry,4,Ambient Pop,Cigarettes After Sex,2019-10-25,Modelo Principal|27|1.75|Estadounidense",
                video.construirFilaCsv());
    }
}