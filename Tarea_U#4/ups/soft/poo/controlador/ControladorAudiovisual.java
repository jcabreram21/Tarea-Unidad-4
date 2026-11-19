package ups.soft.poo.controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import ups.soft.poo.modelo.Actor;
import ups.soft.poo.modelo.Documental;
import ups.soft.poo.modelo.Investigador;
import ups.soft.poo.modelo.Pelicula;
import ups.soft.poo.modelo.Podcast;
import ups.soft.poo.modelo.SerieDeTV;
import ups.soft.poo.modelo.VideoMusical;

// Esta parte maneja las listas y valida los datos antes de guardar para no mezclar esto con la vista
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

    // Se crea una lista vacia por si pasan listas nulas y que no falle
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
        if (!esContenidoBaseValido(titulo, duracionEnMinutos, genero) || !esTextoValido(estudio)) {
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
        // Se toma la pelicula que acabo de meter para agregarle los actores que vengan
        Pelicula creada = peliculas.get(peliculas.size() - 1);
        agregarActoresDesdeLista(actores, creada::agregarActores);
        return true;
    }

    public boolean agregarSerie(
    String titulo,
    int duracionEnMinutos,
    String genero,
    int numeroTemporadas) {
        if (!esContenidoBaseValido(titulo, duracionEnMinutos, genero)
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
        if (!esContenidoBaseValido(titulo, duracionEnMinutos, genero) || !esTextoValido(tema)) {
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
        if (!esContenidoBaseValido(titulo, duracionEnMinutos, genero) || !esTextoValido(artista)) {
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
        agregarActoresDesdeLista(actores, creado::agregarActores);
        return true;
    }

    public boolean agregarPodcast(
    String titulo,
    int duracionEnMinutos,
    String genero,
    int numTemporada,
    int numEpisodio,
    Investigador invitado) {
        if (!esContenidoBaseValido(titulo, duracionEnMinutos, genero)
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
        if (pelicula == null) {
            return false;
        }
        Actor actor = crearActor(nombre, edad, altura, nacionalidad);
        if (actor == null) {
            return false;
        }
        pelicula.agregarActores(actor);
        return true;
    }

    public boolean agregarActorAVideoMusical(
    VideoMusical videoMusical,
    String nombre,
    int edad,
    double altura,
    String nacionalidad) {
        if (videoMusical == null) {
            return false;
        }
        Actor actor = crearActor(nombre, edad, altura, nacionalidad);
        if (actor == null) {
            return false;
        }
        videoMusical.agregarActores(actor);
        return true;
    }

    public boolean asignarInvestigadorADocumental(
    Documental documental,
    String nombre,
    int edad,
    String nacionalidad) {
        if (documental == null) {
            return false;
        }
        Investigador investigador = crearInvestigador(nombre, edad, nacionalidad);
        if (investigador == null) {
            return false;
        }
        documental.setInvestigador(investigador);
        return true;
    }

    public boolean asignarInvitadoAPodcast(
    Podcast podcast,
    String nombre,
    int edad,
    String nacionalidad) {
        if (podcast == null) {
            return false;
        }
        Investigador invitado = crearInvestigador(nombre, edad, nacionalidad);
        if (invitado == null) {
            return false;
        }
        podcast.setInvitado(invitado);
        return true;
    }

    // Devuelvo lista de solo lectura para que no modifiquen las listas internas por fuera
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

    // Reutilizo esta parte para no repetir el mismo for en peliculas y videos musicales
    private void agregarActoresDesdeLista(List<Actor> actores, Consumer<Actor> agregador) {
        if (actores == null || agregador == null) {
            return;
        }
        for (Actor actor : actores) {
            // Salto los nulos para no cortar el resto de actores si uno viene vacio
            if (actor != null) {
                agregador.accept(actor);
            }
        }
    }

    private boolean esContenidoBaseValido(String titulo, int duracionEnMinutos, String genero) {
        return esTextoValido(titulo) && esTextoValido(genero) && esPositivo(duracionEnMinutos);
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