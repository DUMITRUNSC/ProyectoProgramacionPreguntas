package juegoPreguntas;

import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class PreguntaMates extends Pregunta {
    // Constructor
    public PreguntaMates(String enunciado, String respuesta) {
        super(enunciado, respuesta);
    }

    // Método para generar una representación textual de la pregunta de mates
    @Override
    public String generarTextoPregunta() {
        return "Resuelve la siguiente expresión matemática: " + enunciado;
    }

    // Método para generar una pregunta de mates aleatoria
    public static PreguntaMates generarPreguntaAleatoria() {
        Random random = new Random();
        int numOperandos = random.nextInt(5) + 4;

        StringBuilder enunciadoBuilder = new StringBuilder();
        StringBuilder respuestaBuilder = new StringBuilder();

        for (int i = 0; i < numOperandos; i++) {
            int numero = random.nextInt(11) + 2;

            enunciadoBuilder.append(numero);
            respuestaBuilder.append(numero);

            if (i < numOperandos - 1) {
                int operadorIndex = random.nextInt(3);
                String operador;

                switch (operadorIndex) {
                    case 0:
                        operador = " + ";
                        break;
                    case 1:
                        operador = " - ";
                        break;
                    default:
                        operador = " * ";
                        break;
                }

                enunciadoBuilder.append(operador);
                respuestaBuilder.append(operador);
            }
        }

        String enunciado = enunciadoBuilder.toString();
        String respuesta = calcularRespuesta(respuestaBuilder.toString());

        return new PreguntaMates(enunciado, respuesta);
    }

    // Método auxiliar para calcular la respuesta a una expresión matemática
    private static String calcularRespuesta(String expresion) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        Object resultado;

        try {
            resultado = engine.eval(expresion);
        } catch (ScriptException e) {
            resultado = null;
        }

        return resultado != null ? resultado.toString() : "";
    }

    @Override
    public String getRespuestaCorrecta() {
        return respuesta;
    }
}



