package juegoPreguntas;

import java.io.Serializable;
import java.util.List;

public class Partida implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Jugador> jugadores;
    private int numeroDeRondas;
    private int rondaActual;

    public Partida(List<Jugador> jugadores, int numeroDeRondas) {
        this.jugadores = jugadores;
        this.numeroDeRondas = numeroDeRondas;
        this.rondaActual = 1;
    }

    public void jugarRondas() {
        while (rondaActual <= numeroDeRondas) {
            System.out.println("Ronda " + rondaActual + " de " + numeroDeRondas);
            for (Jugador jugador : jugadores) {
                Pregunta pregunta = generarPreguntaAleatoria();
                System.out.println(jugador.getNombre() + ": " + pregunta.getEnunciado());
                String respuesta = ""; // Aquí debería obtener la respuesta del jugador o CPU
                boolean acierto = pregunta.esCorrecta(respuesta);
                if (acierto) {
                    jugador.actualizarPuntuacionTotal(1);
                    System.out.println("Respuesta correcta!");
                } else {
                    System.out.println("Respuesta incorrecta. La respuesta correcta es: " + pregunta.getRespuestaCorrecta());
                }
            }
            rondaActual++;
        }
    }

    public void jugarPartida() {
        jugarRondas();
        mostrarResultadoFinal();
    }

    private Pregunta generarPreguntaAleatoria() {
        int tipoPregunta = (int) (Math.random() * 3);
        switch (tipoPregunta) {
            case 0:
                return new PreguntaMates("", "");
            case 1:
                return new PreguntaLetras("", "");
            case 2:
                return new PreguntaIngles("", "");
            default:
                throw new IllegalStateException("Valor inesperado: " + tipoPregunta);
        }
    }

    public Jugador calcularGanador() {
        if (jugadores.isEmpty()) {
            return null; // No hay jugadores, devuelve null o realiza alguna acción adecuada
        }

        Jugador ganador = jugadores.get(0);
        for (Jugador jugador : jugadores) {
            if (jugador.getPuntuacionTotal() > ganador.getPuntuacionTotal()) {
                ganador = jugador;
            }
        }
        return ganador;
    }

    public void mostrarResultadoFinal() {
        System.out.println("Resultado final:");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre() + ": " + jugador.getPuntuacionTotal() + " puntos");
        }
        Jugador ganador = calcularGanador();
        if (ganador != null) {
            System.out.println("El ganador es " + ganador.getNombre() + " con " + ganador.getPuntuacionTotal() + " puntos!");
        } else {
            System.out.println("No hay ganador.");
        }
    }
}
