package ups.soft.poo.modelo;

public class Documental extends ContenidoAudiovisual {

    private String tema;
    private Investigador investigador;

    public Documental(String titulo, int duracionEnMinutos, String genero, String tema, Investigador investigador) {
    super(titulo, duracionEnMinutos, genero);
    this.tema = tema;
    this.investigador = investigador;
    }

    public Investigador getInvestigador() {
    return investigador;
    }

    public void setInvestigador(Investigador investigador) {
    this.investigador = investigador;
    }

    public String getTema() {
    return tema;
    }

    public void setTema(String tema) {
    this.tema = tema;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles del documental:");
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Tema: " + this.tema);
        System.out.println();
        if (investigador != null) {
            System.out.println("Informacion del investigador principal:");
            investigador.mostrarInvestigador();
        }
    }

    @Override
    public String construirFilaCsv() {
    String datosInvestigador = "";
        if (investigador != null) {
            String nombre = (investigador.getNombre() != null) ? investigador.getNombre() : "";
            String nacionalidad = (investigador.getNacionalidad() != null) ? investigador.getNacionalidad() : "";
            datosInvestigador = nombre + "|" + investigador.getEdad() + "|" + nacionalidad;
        }

        return "Documental,"
        + getTitulo() + ","
        + getDuracionEnMinutos() + ","
        + getGenero() + ","
        + this.tema + ","
        + datosInvestigador;
    }
}