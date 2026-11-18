package ups.soft.poo.modelo;

import java.util.ArrayList;

public class VideoMusical extends ContenidoAudiovisual {

    private String artista;
    private String fechaLanzamiento;
    private ArrayList<Actor> actoresVidMus;

    public VideoMusical(String titulo, int duracionEnMinutos, String genero, String artista) {
        super(titulo, duracionEnMinutos, genero);
        this.artista = artista;
        this.actoresVidMus = new ArrayList<>();
    }

    public void agregarActores(Actor... actores) {
        if (actores == null) {
            return;
        }
        	for (Actor actor : actores) {
        		if (actor != null) {
        			this.actoresVidMus.add(actor);
                }
            }
    }

    public ArrayList<Actor> getActoresVidMus() {
    return actoresVidMus;
    }

    public void setActoresVidMus(ArrayList<Actor> actoresVidMus) {
    this.actoresVidMus = actoresVidMus;
    }

    public String getArtista() {
    return artista;
    }

    public void setArtista(String artista) {
    this.artista = artista;
    }

    public String getFechaLanzamiento() {
    return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
    this.fechaLanzamiento = fechaLanzamiento;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Detalle del video musical:\n"
        + "Título: " + getTitulo()
        + "\nDuración: " + getDuracionEnMinutos() + " min\n"
        + "Género: " + getGenero() + "\n"
        + "Artista: " + artista + "\n");

        if (!actoresVidMus.isEmpty()) {
            System.out.println("Actores Principales: ");
            for (Actor actor : actoresVidMus) {
                if (actor != null) {
                    actor.mostrarDetalles();
                }
            }
        }
    }

    @Override
    public String construirFilaCsv() {
        StringBuilder datosActores = new StringBuilder();
        if (actoresVidMus != null) {
            for (int i = 0; i < actoresVidMus.size(); i++) {
                Actor actorActual = actoresVidMus.get(i);
                if (actorActual == null) {
                    continue;
                }
                	if (datosActores.length() > 0) {
                       datosActores.append(";");
                    }
                datosActores.append(actorActual.getNombre())
                .append("|")
                .append(actorActual.getEdad())
                .append("|")
                .append(actorActual.getAltura())
                .append("|")
                .append(actorActual.getNacionalidad());
            }
        }

        String fecha = (this.fechaLanzamiento != null) ? this.fechaLanzamiento : "";

        return "VideoMusical,"
        + getTitulo() + ","
        + getDuracionEnMinutos() + ","
        + getGenero() + ","
        + this.artista + ","
        + fecha + ","
        + datosActores.toString();
    }
}

