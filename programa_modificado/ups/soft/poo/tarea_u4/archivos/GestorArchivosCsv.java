package ups.soft.poo.tarea_u4.archivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ups.soft.poo.tarea_u2.*;


public class GestorArchivosCsv {

    private static final String DELIMITADOR_CSV = ",";

    public void guardarContenidosEnCsv(String rutaArchivoCsv, List<ContenidoAudiovisual> listaContenidosAudiovisuales) {
        if (rutaArchivoCsv == null || rutaArchivoCsv.trim().isEmpty() || listaContenidosAudiovisuales == null) {
            throw new IllegalArgumentException("La ruta del archivo CSV y la lista de contenidos no pueden ser nulas o vacías.");
        }

        try (BufferedWriter escritorCsv = new BufferedWriter(new FileWriter(rutaArchivoCsv))) {
            for (ContenidoAudiovisual contenidoItem : listaContenidosAudiovisuales) {
                if (contenidoItem != null) {
                    escritorCsv.write(contenidoItem.construirFilaCsv());
                    escritorCsv.newLine();
                }
            }
        } catch (IOException excepcionEscritura) {
            throw new RuntimeException("Error al escribir los contenidos audiovisuales en el archivo CSV: " + rutaArchivoCsv, excepcionEscritura);
        }
    }

    public List<ContenidoAudiovisual> cargarContenidosDesdeCsv(String rutaArchivoCsv) {
        if (rutaArchivoCsv == null || rutaArchivoCsv.trim().isEmpty()) {
            throw new IllegalArgumentException("La ruta del archivo CSV no puede ser nula o vacía.");
        }

        List<ContenidoAudiovisual> contenidosReconstruidos = new ArrayList<>();

        try (BufferedReader lectorCsv = new BufferedReader(new FileReader(rutaArchivoCsv))) {
            String filaLeidaCsv;

            while ((filaLeidaCsv = lectorCsv.readLine()) != null) {
                if (!filaLeidaCsv.trim().isEmpty()) {
                    ContenidoAudiovisual contenidoInstanciado = reconstruirContenidoCsv(filaLeidaCsv);
                    if (contenidoInstanciado != null) {
                        contenidosReconstruidos.add(contenidoInstanciado);
                    }
                }
            }
        } catch (IOException excepcionLectura) {
            throw new RuntimeException("Error al leer los contenidos audiovisuales desde el archivo CSV: " + rutaArchivoCsv, excepcionLectura);
        }

        return contenidosReconstruidos;
    }

    private ContenidoAudiovisual reconstruirContenidoCsv(String filaLeidaCsv) {
        String[] columnasCsv = filaLeidaCsv.split(DELIMITADOR_CSV, -1);
        String tipoContenidoAudiovisual = columnasCsv[0].trim();

        switch (tipoContenidoAudiovisual) {
        case "Documental": {
            String titulo = columnasCsv.length > 1 ? columnasCsv[1].trim() : "";
            String textoDuracion = columnasCsv.length > 2 ? columnasCsv[2].trim() : "";
            int duracionEnMinutos = textoDuracion.isEmpty() ? 0 : Integer.parseInt(textoDuracion);
            String genero = columnasCsv.length > 3 ? columnasCsv[3].trim() : "";
            String tema = columnasCsv.length > 4 ? columnasCsv[4].trim() : "";
            String nombreInvestigador = columnasCsv.length > 5 ? columnasCsv[5].trim() : "";

            Investigador investigador = null;
            	if (!nombreInvestigador.isEmpty()) {
                   investigador = new Investigador(nombreInvestigador, 0, "");
                }
         return new Documental(titulo, duracionEnMinutos, genero, tema, investigador);
         }
                 default:
                 return null;
                
        case "Pelicula": {
            String titulo = columnasCsv.length > 1 ? columnasCsv[1].trim() : "";
            String textoDuracion = columnasCsv.length > 2 ? columnasCsv[2].trim() : "";
            int duracionEnMinutos = textoDuracion.isEmpty() ? 0 : Integer.parseInt(textoDuracion);
            String genero = columnasCsv.length > 3 ? columnasCsv[3].trim() : "";
            String estudio = columnasCsv.length > 4 ? columnasCsv[4].trim() : "";
            String actoresCsv = columnasCsv.length > 5 ? columnasCsv[5].trim() : "";

            Pelicula pelicula = new Pelicula(titulo, duracionEnMinutos, genero, estudio);

            	if (!actoresCsv.isEmpty()) {
                    String[] nombresActores = actoresCsv.split(";");
                    for (String nombreActor : nombresActores) {
                        String nombreLimpio = nombreActor.trim();
                        if (!nombreLimpio.isEmpty()) {
                            pelicula.agregarActores(new Actor(nombreLimpio, 0, 0.0, ""));
                        }
                    }
                }

                return pelicula;
                
                
            }
        
        
        case "Podcast": {
            String titulo = columnasCsv.length > 1 ? columnasCsv[1].trim() : "";
            String textoDuracion = columnasCsv.length > 2 ? columnasCsv[2].trim() : "";
            int duracionEnMinutos = textoDuracion.isEmpty() ? 0 : Integer.parseInt(textoDuracion);
            String genero = columnasCsv.length > 3 ? columnasCsv[3].trim() : "";

            String textoTemporada = columnasCsv.length > 4 ? columnasCsv[4].trim() : "";
            int numTemporada = textoTemporada.isEmpty() ? 0 : Integer.parseInt(textoTemporada);

            String textoEpisodio = columnasCsv.length > 5 ? columnasCsv[5].trim() : "";
            int numEpisodio = textoEpisodio.isEmpty() ? 0 : Integer.parseInt(textoEpisodio);

            String nombreInvitado = columnasCsv.length > 6 ? columnasCsv[6].trim() : "";
            Investigador invitado = null;
            	if (!nombreInvitado.isEmpty()) {
                   invitado = new Investigador(nombreInvitado, 0, "");
            }

                return new Podcast(titulo, duracionEnMinutos, genero, numTemporada, numEpisodio, invitado);
         }
        
        case "SerieDeTV": {
            String titulo = columnasCsv.length > 1 ? columnasCsv[1].trim() : "";
            String textoDuracion = columnasCsv.length > 2 ? columnasCsv[2].trim() : "";
            int duracionEnMinutos = textoDuracion.isEmpty() ? 0 : Integer.parseInt(textoDuracion);
            String genero = columnasCsv.length > 3 ? columnasCsv[3].trim() : "";

            String textoTotalTemporadas = columnasCsv.length > 4 ? columnasCsv[4].trim() : "";
            int totalTemporadas = textoTotalTemporadas.isEmpty() ? 0 : Integer.parseInt(textoTotalTemporadas);

            String temporadasCsv = columnasCsv.length > 5 ? columnasCsv[5].trim() : "";
            SerieDeTV serie = new SerieDeTV(titulo, duracionEnMinutos, genero, totalTemporadas);

            	if (!temporadasCsv.isEmpty()) {
                   String[] bloquesTemporada = temporadasCsv.split(";");
                	for (String bloque : bloquesTemporada) {
                       String limpio = bloque.trim().replace("ep", "");
                    	if (!limpio.isEmpty() && limpio.contains("-")) {
                           String[] partes = limpio.split("-");
                           String numTempTexto = partes[0].replaceFirst("^[Tt]", "").trim();
                           String numCapTexto = partes[1].trim();

                        int numTemporada = numTempTexto.isEmpty() ? 0 : Integer.parseInt(numTempTexto);
                        int numCapitulos = numCapTexto.isEmpty() ? 0 : Integer.parseInt(numCapTexto);

                        // Ajusta este new Temporada(...) si su constructor pide más parámetros
                        serie.agregarTemporada(new Temporada(numTemporada, numCapitulos));
                    }
                }
            }

            return serie;
        }
        
        case "VideoMusical": {
            String titulo = columnasCsv.length > 1 ? columnasCsv[1].trim() : "";
            String textoDuracion = columnasCsv.length > 2 ? columnasCsv[2].trim() : "";
            int duracionEnMinutos = textoDuracion.isEmpty() ? 0 : Integer.parseInt(textoDuracion);
            String genero = columnasCsv.length > 3 ? columnasCsv[3].trim() : "";
            String artista = columnasCsv.length > 4 ? columnasCsv[4].trim() : "";
            String fechaLanzamiento = columnasCsv.length > 5 ? columnasCsv[5].trim() : "";
            String actoresCsv = columnasCsv.length > 6 ? columnasCsv[6].trim() : "";

            VideoMusical videoMusical = new VideoMusical(titulo, duracionEnMinutos, genero, artista);
            videoMusical.setFechaLanzamiento(fechaLanzamiento);

            	if (!actoresCsv.isEmpty()) {
                   String[] nombresActores = actoresCsv.split(";");
                	for (String nombreActor : nombresActores) {
                        String nombreLimpio = nombreActor.trim();
                    	if (!nombreLimpio.isEmpty()) {
                           videoMusical.agregarActores(new Actor(nombreLimpio, 0, 0.0, ""));
                    }
                }
            }

            return videoMusical;
        }
        }
        
        
    }
    
}