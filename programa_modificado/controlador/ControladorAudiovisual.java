package controlador;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ups.soft.poo.tarea_u2.Actor;
import ups.soft.poo.tarea_u2.Documental;
import ups.soft.poo.tarea_u2.Investigador;
import ups.soft.poo.tarea_u2.Pelicula;
import ups.soft.poo.tarea_u2.Podcast;
import ups.soft.poo.tarea_u2.SerieDeTV;
import ups.soft.poo.tarea_u2.VideoMusical;

public class ControladorAudiovisual {

    private final List<Pelicula> peliculas;
    private final List<SerieDeTV> series;
    private final List<Documental> documentales;
    private final List<VideoMusical> videosMusicales;
    private final List<Podcast> podcasts;

    public ControladorAudiovisual() {
        this.peliculas = new ArrayList<>();
        this.series = new ArrayList<>();
        this.documentales = new ArrayList<>();
        this.videosMusicales = new ArrayList<>();
        this.podcasts = new ArrayList<>();
    }

    public ControladorAudiovisual(
            List<Pelicula> peliculas,
            List<SerieDeTV> series,
            List<Documental> documentales,
            List<VideoMusical> videosMusicales,
            List<Podcast> podcasts) {
        this.peliculas = (peliculas != null) ? peliculas : new ArrayList<>();
        this.series = (series != null) ? series : new ArrayList<>();
        this.documentales = (documentales != null) ? documentales : new ArrayList<>();
        this.videosMusicales = (videosMusicales != null) ? videosMusicales : new ArrayList<>();
        this.podcasts = (podcasts != null) ? podcasts : new ArrayList<>();
    }

    public boolean agregarPelicula(
            String titulo,
            int duracionEnMinutos,
            String genero,
            String estudio) {
        if (!esTextoValido(titulo)
                || !esTextoValido(genero)
                || !esTextoValido(estudio)
                || !esPositivo(duracionEnMinutos)) {
            return false;
        }
        peliculas.add(new Pelicula(
                titulo.trim(),
                duracionEnMinutos,
                genero.trim(),
                estudio.trim()));
        return true;
    }

    public boolean agregarPelicula(
            String titulo,
            int duracionEnMinutos,
            String genero,
            String estudio,
            List<Actor> actores) {
        if (!agregarPelicula(titulo, duracionEnMinutos, genero, estudio)) {
            return false;
        }
        Pelicula creada = peliculas.get(peliculas.size() - 1);
        agregarActoresAPeliculaDesdeLista(creada, actores);
        return true;
    }

    public boolean agregarSerie(
            String titulo,
            int duracionEnMinutos,
            String genero,
            int numeroTemporadas) {
        if (!esTextoValido(titulo)
                || !esTextoValido(genero)
                || !esPositivo(duracionEnMinutos)
                || !esPositivo(numeroTemporadas)) {
            return false;
        }
        series.add(new SerieDeTV(
                titulo.trim(),
                duracionEnMinutos,
                genero.trim(),
                numeroTemporadas));
        return true;
    }

    public boolean agregarDocumental(
            String titulo,
            int duracionEnMinutos,
            String genero,
            String tema,
            Investigador investigador) {
        if (!esTextoValido(titulo)
                || !esTextoValido(genero)
                || !esTextoValido(tema)
                || !esPositivo(duracionEnMinutos)) {
            return false;
        }
        documentales.add(new Documental(
                titulo.trim(),
                duracionEnMinutos,
                genero.trim(),
                tema.trim(),
                investigador));
        return true;
    }

    public boolean agregarVideoMusical(
            String titulo,
            int duracionEnMinutos,
            String genero,
            String artista) {
        if (!esTextoValido(titulo)
                || !esTextoValido(genero)
                || !esTextoValido(artista)
                || !esPositivo(duracionEnMinutos)) {
            return false;
        }
        videosMusicales.add(new VideoMusical(
                titulo.trim(),
                duracionEnMinutos,
                genero.trim(),
                artista.trim()));
        return true;
    }

    public boolean agregarVideoMusical(
            String titulo,
            int duracionEnMinutos,
            String genero,
            String artista,
            List<Actor> actores) {
        if (!agregarVideoMusical(titulo, duracionEnMinutos, genero, artista)) {
            return false;
        }
        VideoMusical creado = videosMusicales.get(videosMusicales.size() - 1);
        agregarActoresAVideoDesdeLista(creado, actores);
        return true;
    }

    public boolean agregarPodcast(
            String titulo,
            int duracionEnMinutos,
            String genero,
            int numTemporada,
            int numEpisodio,
            Investigador invitado) {
        if (!esTextoValido(titulo)
                || !esTextoValido(genero)
                || !esPositivo(duracionEnMinutos)
                || !esPositivo(numTemporada)
                || !esPositivo(numEpisodio)) {
            return false;
        }
        podcasts.add(new Podcast(
                titulo.trim(),
                duracionEnMinutos,
                genero.trim(),
                numTemporada,
                numEpisodio,
                invitado));
        return true;
    }

    public Actor crearActor(
            String nombre,
            int edad,
            double altura,
            String nacionalidad) {
        if (!esActorValido(nombre, edad, altura, nacionalidad)) {
            return null;
        }
        return new Actor(nombre.trim(), edad, altura, nacionalidad.trim());
    }

    public Investigador crearInvestigador(
            String nombre,
            int edad,
            String nacionalidad) {
        if (!esInvestigadorValido(nombre, edad, nacionalidad)) {
            return null;
        }
        return new Investigador(nombre.trim(), edad, nacionalidad.trim());
    }

    public boolean agregarActorAPelicula(
            Pelicula pelicula,
            String nombre,
            int edad,
            double altura,
            String nacionalidad) {
        if (pelicula == null || !esActorValido(nombre, edad, altura, nacionalidad)) {
            return false;
        }
        pelicula.agregarActores(crearActor(nombre, edad, altura, nacionalidad));
        return true;
    }

    public boolean agregarActorAVideoMusical(
            VideoMusical videoMusical,
            String nombre,
            int edad,
            double altura,
            String nacionalidad) {
        if (videoMusical == null || !esActorValido(nombre, edad, altura, nacionalidad)) {
            return false;
        }
        videoMusical.agregarActores(crearActor(nombre, edad, altura, nacionalidad));
        return true;
    }

    public boolean asignarInvestigadorADocumental(
            Documental documental,
            String nombre,
            int edad,
            String nacionalidad) {
        if (documental == null || !esInvestigadorValido(nombre, edad, nacionalidad)) {
            return false;
        }
        documental.setInvestigador(crearInvestigador(nombre, edad, nacionalidad));
        return true;
    }

    public boolean asignarInvitadoAPodcast(
            Podcast podcast,
            String nombre,
            int edad,
            String nacionalidad) {
        if (podcast == null || !esInvestigadorValido(nombre, edad, nacionalidad)) {
            return false;
        }
        podcast.setInvitado(crearInvestigador(nombre, edad, nacionalidad));
        return true;
    }

    public List<Pelicula> getPeliculas() {
        return Collections.unmodifiableList(peliculas);
    }

    public List<SerieDeTV> getSeries() {
        return Collections.unmodifiableList(series);
    }

    public List<Documental> getDocumentales() {
        return Collections.unmodifiableList(documentales);
    }

    public List<VideoMusical> getVideosMusicales() {
        return Collections.unmodifiableList(videosMusicales);
    }

    public List<Podcast> getPodcasts() {
        return Collections.unmodifiableList(podcasts);
    }

    private void agregarActoresAPeliculaDesdeLista(Pelicula pelicula, List<Actor> actores) {
        if (actores == null) {
            return;
        }
        for (Actor actor : actores) {
            if (actor != null) {
                pelicula.agregarActores(actor);
            }
        }
    }

    private void agregarActoresAVideoDesdeLista(VideoMusical video, List<Actor> actores) {
        if (actores == null) {
            return;
        }
        for (Actor actor : actores) {
            if (actor != null) {
                video.agregarActores(actor);
            }
        }
    }

    private boolean esTextoValido(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }

    private boolean esPositivo(int valor) {
        return valor > 0;
    }

    private boolean esActorValido(
            String nombre,
            int edad,
            double altura,
            String nacionalidad) {
        return esTextoValido(nombre)
                && esTextoValido(nacionalidad)
                && esPositivo(edad)
                && altura > 0;
    }

    private boolean esInvestigadorValido(
            String nombre,
            int edad,
            String nacionalidad) {
        return esTextoValido(nombre)
                && esTextoValido(nacionalidad)
                && esPositivo(edad);
    }
}