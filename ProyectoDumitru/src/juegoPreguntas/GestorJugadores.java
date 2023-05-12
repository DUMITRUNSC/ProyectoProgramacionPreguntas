package juegoPreguntas;

import java.io.*;
import java.util.*;

public class GestorJugadores {

    private List<Jugador> jugadores;
    private static final String ARCHIVO_JUGADORES = "jugadores.dat";

    // Constructor
    public GestorJugadores() {
        jugadores = cargarJugadores();
    }

    // Método para añadir un jugador
    public void anadirJugador(Jugador jugador) {
        jugadores.add(jugador);
        guardarJugadores();
    }

    // Método para eliminar un jugador
    public void eliminarJugador(String nombre) {
        jugadores.removeIf(jugador -> jugador.getNombre().equals(nombre));
        guardarJugadores();
    }

    // Método para mostrar la lista de jugadores registrados
    public void mostrarJugadores() {
        System.out.println("Lista de jugadores:");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador);
        }
    }

    // Método para obtener la lista de jugadores
    public List<Jugador> getJugadores() {
        return new ArrayList<>(jugadores);
    }

    // Método para guardar los jugadores en un archivo
    private void guardarJugadores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_JUGADORES))) {
            oos.writeObject(jugadores);
        } catch (IOException e) {
            System.err.println("Error al guardar los jugadores: " + e.getMessage());
        }
    }

    // Método para cargar los jugadores desde un archivo
    @SuppressWarnings("unchecked")
    private List<Jugador> cargarJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_JUGADORES))) {
            jugadores = (List<Jugador>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo jugadores.dat. Se creará uno nuevo.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los jugadores: " + e.getMessage());
        }
        return jugadores;
    }
}




