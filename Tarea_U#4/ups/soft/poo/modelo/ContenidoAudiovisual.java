package ups.soft.poo.modelo;

public abstract class ContenidoAudiovisual {

    private static int contadorId = 1;

    private final int id;
    private String titulo;
    private int duracionEnMinutos;
    private String genero;

    protected ContenidoAudiovisual(String titulo, int duracionEnMinutos, String genero) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.duracionEnMinutos = duracionEnMinutos;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public void setDuracionEnMinutos(int duracionEnMinutos) {
        this.duracionEnMinutos = duracionEnMinutos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public abstract String construirFilaCsv();

    public abstract void mostrarDetalles();
}