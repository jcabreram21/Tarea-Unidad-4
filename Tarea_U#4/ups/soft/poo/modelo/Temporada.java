package ups.soft.poo.modelo;


public class Temporada {

    private int numTemporada;
    private int numCapitulos;

    public Temporada(int numTemporada, int numCapitulos) {
    this.numTemporada = numTemporada;
    this.numCapitulos = numCapitulos;
    }

    public int getNumTemporada() {
    return numTemporada;
    }

    public void setNumTemporada(int numTemporada) {
    this.numTemporada = numTemporada;
    }

    public int getNumCapitulos() {
    return numCapitulos;
    }

    public void setNumCapitulos(int numCapitulos) {
    this.numCapitulos = numCapitulos;
    }
}