package introduction.impl;

import introduction.app.GestorTareas;
import introduction.app.Tarea;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * Menu en consola para gestionar tareas personales.
 * Operaciones: agregar, listar, completar, eliminar completadas.
 * Extra: guardar/cargar desde archivo de texto.
 */
public final class MainIntro {
    private static final Path ARCHIVO = Path.of("tareas.txt");
    // Si usas Java 8: reemplazar por Paths.get("tareas.txt");

    public static void main(String[] args) {
        GestorTareas gestor = new GestorTareas();

        // Carga inicial si existe
        try { gestor.cargar(ARCHIVO); }
        catch (Exception e) { System.out.println("Aviso: no se pudieron cargar tareas previas (" + e.getMessage() + ")"); }

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1": // Agregar
                    System.out.print("Descripcion de la tarea: ");
                    String desc = sc.nextLine().trim();
                    if (desc.isEmpty()) {
                        System.out.println("Error: la descripcion no puede ser vacia.");
                    } else {
                        try {
                            gestor.agregar(desc);
                            System.out.println("Tarea agregada.");
                        } catch (IllegalArgumentException ex) {
                            System.out.println("Error: " + ex.getMessage());
                        }
                    }
                    break;

                case "2": // Listar
                    listar(gestor.listar());
                    break;

                case "3": // Completar
                    listar(gestor.listar());
                    if (gestor.listar().isEmpty()) break;
                    System.out.print("Indice de la tarea a completar (1..n): ");
                    Integer idx = leerEntero(sc);
                    if (idx == null) {
                        System.out.println("Entrada invalida.");
                    } else if (gestor.marcarComoCompletada(idx)) {
                        System.out.println("Tarea marcada como COMPLETADA.");
                    } else {
                        System.out.println("Indice fuera de rango.");
                    }
                    break;

                case "4": // Eliminar completadas
                    int eliminadas = gestor.eliminarCompletadas();
                    System.out.println("Se eliminaron " + eliminadas + " tareas completadas.");
                    break;

                case "5": // Guardar
                    try {
                        gestor.guardar(ARCHIVO);
                        System.out.println("Tareas guardadas en " + ARCHIVO.toAbsolutePath());
                    } catch (Exception e) {
                        System.out.println("Error al guardar: " + e.getMessage());
                    }
                    break;

                case "6": // Cargar
                    try {
                        gestor.cargar(ARCHIVO);
                        System.out.println("Tareas cargadas desde " + ARCHIVO.toAbsolutePath());
                    } catch (Exception e) {
                        System.out.println("Error al cargar: " + e.getMessage());
                    }
                    break;

                case "0": // Salir
                    continuar = false;
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }
            System.out.println();
        }

        // Guardado final opcional
        try { gestor.guardar(ARCHIVO); } catch (Exception ignored) {}
        System.out.println("Hasta luego.");
    }

    private static void mostrarMenu() {
        System.out.println("=== Gestor de Tareas ===");
        System.out.println("1) Agregar tarea");
        System.out.println("2) Listar tareas");
        System.out.println("3) Marcar como completada");
        System.out.println("4) Eliminar tareas completadas");
        System.out.println("5) Guardar en archivo (extra)");
        System.out.println("6) Cargar desde archivo (extra)");
        System.out.println("0) Salir");
        System.out.print("Elige una opcion: ");
    }

    private static void listar(List<Tarea> tareas) {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas.");
            return;
        }
        int i = 1;
        for (Tarea t : tareas) {
            System.out.println(String.format("%2d. %s", i++, t));
        }
    }

    private static Integer leerEntero(Scanner sc) {
        String s = sc.nextLine().trim();
        try { return Integer.parseInt(s); }
        catch (NumberFormatException e) { return null; }
    }
}
