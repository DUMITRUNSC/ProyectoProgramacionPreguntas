package juegoPreguntas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PreguntaLetras extends Pregunta {
    private static final String DICCIONARIO_PATH = "diccionario.txt";

    // Constructor
    public PreguntaLetras(String enunciado, String respuesta) {
        super(enunciado, respuesta);
    }

    // Método para generar una representación textual de la pregunta de letras
    @Override
    public String generarTextoPregunta() {
        return "Adivina la siguiente palabra oculta: " + enunciado;
    }

    // Método para generar una pregunta de letras aleatoria
    public static PreguntaLetras generarPreguntaAleatoria() {
        String palabra = obtenerPalabraAleatoria();
        String enunciado = ocultarLetras(palabra);

        return new PreguntaLetras(enunciado, palabra);
    }

    // Método auxiliar para obtener una palabra aleatoria del archivo diccionario.txt
    private static String obtenerPalabraAleatoria() {
        List<String> palabras = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(DICCIONARIO_PATH))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                palabras.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!palabras.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(palabras.size());
            return palabras.get(index);
        } else {
            return "";
        }
    }

    // Método auxiliar para ocultar algunas letras de la palabra seleccionada
    private static String ocultarLetras(String palabra) {
        int longitud = palabra.length();
        int numOcultas = longitud / 3;

        StringBuilder enunciado = new StringBuilder(palabra);

        Random random = new Random();
        for (int i = 0; i < numOcultas; i++) {
            int index = random.nextInt(longitud);
            enunciado.setCharAt(index, '*');
        }

        return enunciado.toString();
    }

    @Override
    public String getRespuestaCorrecta() {
        return respuesta;
    }
}

