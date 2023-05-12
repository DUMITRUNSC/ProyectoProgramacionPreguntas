package juegoPreguntas;

import java.util.Scanner;
import java.util.List;

public class MainMenuPrincipal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorJugadores gestorJugadores = new GestorJugadores();
        GestorRanking gestorRanking = new GestorRanking();
        GestorHistorico gestorHistorico = new GestorHistorico();

        int opcion;
        
       
     
        do {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Jugar Partida");
            System.out.println("2. Ranking");
            System.out.println("3. Histórico");
            System.out.println("4. Jugadores");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Jugar partida
                    List<Jugador> jugadores = gestorJugadores.getJugadores();
                    Partida partida = new Partida(jugadores, opcion);
                    partida.jugarPartida();
                    gestorHistorico.anadirPartida(partida);
                    break;
                case 2:
                    // Mostrar ranking
                    gestorRanking.mostrarRanking();
                    break;
                case 3:
                    // Mostrar histórico
                    gestorHistorico.mostrarHistorico();
                    break;
                case 4:
                    // Submenú de gestión de jugadores
                    int opcionJugadores;
                    do {
                        System.out.println("\nGestión de Jugadores:");
                        System.out.println("1. Ver jugadores");
                        System.out.println("2. Añadir jugador");
                        System.out.println("3. Eliminar jugador");
                        System.out.println("4. Volver");
                        System.out.print("Seleccione una opción: ");
                        opcionJugadores = scanner.nextInt();

                        switch (opcionJugadores) {
                            case 1:
                                // Ver jugadores
                                gestorJugadores.mostrarJugadores();
                                break;
                            case 2:
                                // Añadir jugador
                                System.out.print("Introduzca el nombre del jugador: ");
                                String nombre = scanner.next();
                                Jugador jugador = new Jugador(nombre, 0);
                                gestorJugadores.anadirJugador(jugador);
                                break;
                            case 3:
                                // Eliminar jugador
                                System.out.print("Introduzca el nombre del jugador a eliminar: ");
                                String nombreEliminar = scanner.next();
                                gestorJugadores.eliminarJugador(nombreEliminar);
                                break;
                            case 4:
                                // Volver al menú principal
                                break;
                            default:
                                System.out.println("Opción inválida. Intente de nuevo.");
                        }
                    } while (opcionJugadores != 4);
                    break;
                case 5:
                    // Salir del programa
                    System.out.println("Adiós!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}


