package juegoPreguntas;

	import java.util.*;

	public class GestorRanking {

	    private Map<String, Integer> ranking;

	    // Constructor
	    public GestorRanking() {
	        ranking = new HashMap<>();
	    }

	    // Método para añadir un jugador al ranking
	    public void anadirJugador(String nombreJugador) {
	        if (!ranking.containsKey(nombreJugador)) {
	            ranking.put(nombreJugador, 0);
	        }
	    }

	    // Método para eliminar un jugador del ranking
	    public void eliminarJugador(String nombreJugador) {
	        ranking.remove(nombreJugador);
	    }

	    // Método para actualizar la puntuación de un jugador
	    public void actualizarPuntuacion(String nombreJugador, int puntuacion) {
	        if (ranking.containsKey(nombreJugador)) {
	            int puntuacionActual = ranking.get(nombreJugador);
	            ranking.put(nombreJugador, puntuacionActual + puntuacion);
	        }
	    }

	    // Método para mostrar el ranking
	    public void mostrarRanking() {
	        List<Map.Entry<String, Integer>> list = new ArrayList<>(ranking.entrySet());
	        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

	        System.out.println("Ranking de jugadores:");
	        for (Map.Entry<String, Integer> entry : list) {
	            System.out.println(entry.getKey() + ": " + entry.getValue() + " puntos");
	        }
	    }
	}

