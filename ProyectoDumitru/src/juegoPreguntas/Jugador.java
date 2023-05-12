package juegoPreguntas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Jugador implements Serializable{
    private static final long serialVersionUID = 1L;
	private String nombre;
    private int puntuacionTotal; // Cambiar a instancia en lugar de estática

    // Constructor
    public Jugador(String nombre, int puntuacionTotal) {
        this.nombre = nombre;
        this.puntuacionTotal = puntuacionTotal; // Modificar la variable de instancia
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(int puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal; // Modificar la variable de instancia
    }

    // Método estático para crear una instancia de Jugador desde un String
 // Método estático para crear una instancia de Jugador desde un String
    public static Jugador desdeString(String linea) {
        String[] datos = linea.split(",");
        if (datos.length != 2) {
            throw new IllegalArgumentException("Formato de datos incorrecto para el jugador: " + linea);
        }
        String nombre = datos[0];
        int puntuacionTotal = Integer.parseInt(datos[1]);
        return new Jugador(nombre, puntuacionTotal);
    }




    // Método para actualizar la puntuación total del jugador
    public void actualizarPuntuacionTotal(int puntos) {
        puntuacionTotal += puntos;
    }

    // Método para guardar la información del jugador en un archivo
    public void guardarJugador(String archivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(nombre + "," + puntuacionTotal);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar la información del jugador desde un archivo
    public static Jugador cargarJugador(String archivo, String nombre) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equalsIgnoreCase(nombre)) {
                    int puntuacionTotal = Integer.parseInt(datos[1]);
                    Jugador jugador = new Jugador(nombre, puntuacionTotal);
                    return jugador;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}


