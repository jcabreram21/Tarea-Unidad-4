package ups.soft.poo.modelo;

import java.util.ArrayList;

public class Pelicula extends ContenidoAudiovisual {

    private String estudio;
    private ArrayList<Actor> actores;

    public Pelicula(String titulo, int duracionEnMinutos, String genero, String estudio) {
    super(titulo, duracionEnMinutos, genero);
    this.estudio = estudio;
    this.actores = new ArrayList<>();
    }

    public void agregarActores(Actor... actores) {
        if (actores == null) {
            return;
        }
        	for (Actor actor : actores) {
        		if (actor != null) {
                this.actores.add(actor);
                }
            }
    }

    public String getEstudio() {
    return estudio;
    }

    public void setEstudio(String estudio) {
    this.estudio = estudio;
    }

    public ArrayList<Actor> getActores() {
    return actores;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("\nDetalles de la película:");
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Estudio: " + estudio);
        System.out.println("\nActores principales:");

        for (Actor actor : actores) {
            if (actor != null) {
                actor.mostrarDetalles();
            }
        }
    }

    @Override
    public String construirFilaCsv() {
        StringBuilder datosActores = new StringBuilder();
        if (actores != null) {
            for (int i = 0; i < actores.size(); i++) {
                Actor actorActual = actores.get(i);
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

        return "Pelicula,"
        + getTitulo() + ","
        + getDuracionEnMinutos() +      ","
        + getGenero() + ","
        + this.estudio + ","
        + datosActores.toString();
    }
}