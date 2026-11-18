/**
 * Class SerieDeTV
 */
package ups.soft.poo.tarea_u2;
import java.util.ArrayList;
// Subclase SerieDeTV que extiende de ContenidoAudiovisual
public class SerieDeTV extends ContenidoAudiovisual {
    
    private ArrayList<Temporada> temporadas;
    private int totalTemporadas;
    
    // Se crea el constructor
    public SerieDeTV(String titulo, int duracionEnMinutos, String genero, int totalTemporadas) {
        super(titulo, duracionEnMinutos, genero);
        this.temporadas = new ArrayList<>();
        this.totalTemporadas = totalTemporadas;
    }
    // Se crea un metodo para agregar temporadas
    public void agregarTemporada(Temporada temporada ) {
        this.temporadas.add(temporada);
    }
    
    // Se crean metodos getters y setters
    public ArrayList<Temporada> getTemporadas() {
		return temporadas;
	
    }

    public void setTemporadas(ArrayList<Temporada> temporadas) {
		this.temporadas = temporadas;
	}


	public int getTotalTemporadas() {
	return totalTemporadas;
}


	public void setTotalTemporadas(int totalTemporadas) {
	this.totalTemporadas = totalTemporadas;
}

    // Se configura el metodo para mostrar detalles y datos de las temporadas, y este metodo usara polimorfismo
	@Override
	public void mostrarDetalles() {
        System.out.println("Detalles de la Serie:");
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Temporadas: " + totalTemporadas);
       
        for(Temporada t : temporadas) {
            System.out.println("- Temporada " + t.getNumTemporada() + " (" + t.getNumCapitulos() + " episodios)");
    
        }
    }
	@Override
    public String construirFilaCsv() {
        StringBuilder detalleTemporadas = new StringBuilder();
        if (temporadas != null) {
            for (int i = 0; i < temporadas.size(); i++) {
                Temporada t = temporadas.get(i);
                if (t == null) {
                    continue;
                }
                if (detalleTemporadas.length() > 0) {
                    detalleTemporadas.append(";");
                }
                detalleTemporadas.append(t.getNumTemporada())
                        .append("-")
                        .append(t.getNumCapitulos());
            }
        }

        int cantidadTemporadas = (temporadas != null && !temporadas.isEmpty())
                ? temporadas.size()
                : this.totalTemporadas;

        return "SerieDeTV,"
                + getTitulo() + ","
                + getDuracionEnMinutos() + ","
                + getGenero() + ","
                + cantidadTemporadas + ","
                + detalleTemporadas.toString();
     }
   }
    

	