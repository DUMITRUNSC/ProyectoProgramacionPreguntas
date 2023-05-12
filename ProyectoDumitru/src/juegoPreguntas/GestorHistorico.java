package juegoPreguntas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorHistorico {

    private List<Partida> historico;
    private static final String ARCHIVO_HISTORICO = "historico.dat";

    public GestorHistorico() {
        historico = cargarHistorico();
    }

    public void anadirPartida(Partida partida) {
        historico.add(partida);
        guardarHistorico();
    }

    public void mostrarHistorico() {
        System.out.println("Historial de partidas:");
        for (Partida partida : historico) {
            System.out.println(partida);
        }
    }

    private void guardarHistorico() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_HISTORICO))) {
            oos.writeObject(historico);
        } catch (IOException e) {
            System.err.println("Error al guardar el historial: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<Partida> cargarHistorico() {
        List<Partida> historico = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_HISTORICO))) {
            historico = (List<Partida>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo historico.dat. Se creará uno nuevo.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar el historial: " + e.getMessage());
        }
        return historico;
    }
}


