package juegoPreguntas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PreguntaIngles extends Pregunta {
    private static final String ARCHIVO_INGLES = "ingles.txt";
    private List<String> opciones;

    // Constructor
    public PreguntaIngles(String enunciado, String respuesta) {
        super(enunciado, respuesta);
        opciones = new ArrayList<>();
    }

    // Método para generar una representación textual de la pregunta de inglés
    @Override
    public String generarTextoPregunta() {
        StringBuilder sb = new StringBuilder();
        sb.append("Responde a la siguiente pregunta en inglés:\n");
        sb.append(enunciado).append("\n");
        int opcion = 1;
        for (String o : opciones) {
            sb.append(opcion).append(". ").append(o).append("\n");
            opcion++;
        }
        return sb.toString();
    }

    // Método para generar una pregunta de inglés aleatoria
    public static PreguntaIngles generarPreguntaAleatoria() {
        List<String[]> preguntas = cargarPreguntasDeArchivo();
        Random random = new Random();
        int index = random.nextInt(preguntas.size());

        String[] preguntaSeleccionada = preguntas.get(index);
        String enunciado = preguntaSeleccionada[0];
        String respuesta = preguntaSeleccionada[1];

        PreguntaIngles pregunta = new PreguntaIngles(enunciado, respuesta);

        pregunta.opciones.clear();
        pregunta.opciones.add(respuesta);
        for (int i = 2; i < preguntaSeleccionada.length; i++) {
            pregunta.opciones.add(preguntaSeleccionada[i]);
        }
        Collections.shuffle(pregunta.opciones);

        return pregunta;
    }

    // Método auxiliar para cargar las preguntas de inglés del archivo
    private static List<String[]> cargarPreguntasDeArchivo() {
        List<String[]> preguntas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_INGLES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] pregunta = new String[5];
                pregunta[0] = linea;
                for (int i = 1; i < 5; i++) {
                    pregunta[i] = br.readLine();
                }
                preguntas.add(pregunta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return preguntas;
    }

    @Override
    public String getRespuestaCorrecta() {
        return respuesta;
    }
}
    



