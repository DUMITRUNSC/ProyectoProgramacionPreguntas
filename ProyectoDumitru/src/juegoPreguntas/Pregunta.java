package juegoPreguntas;


public abstract class Pregunta {
    // Atributos
    protected String enunciado;
    protected String respuesta;

    // Constructor
    public Pregunta(String enunciado, String respuesta) {
        this.enunciado = enunciado;
        this.respuesta = respuesta;
    }

    // Getters
    public String getEnunciado() {
        return enunciado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    // Método para verificar si una respuesta es correcta
    public boolean esCorrecta(String respuestaUsuario) {
        return respuesta.equalsIgnoreCase(respuestaUsuario);
    }

    // Método para generar una representación textual de la pregunta
    public abstract String generarTextoPregunta();

    // Método abstracto para obtener la respuesta correcta
    public abstract String getRespuestaCorrecta();

    // Método para generar una pregunta aleatoria
    public static Pregunta generarPreguntaAleatoria() {
        int tipoPregunta = (int) (Math.random() * 3);
        switch (tipoPregunta) {
            case 0:
                return PreguntaMates.generarPreguntaAleatoria();
            case 1:
                return PreguntaLetras.generarPreguntaAleatoria();
            case 2:
                return PreguntaIngles.generarPreguntaAleatoria();
            default:
                throw new IllegalStateException("Tipo de pregunta inválido: " + tipoPregunta);
        }
    }
}
