package introduction.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gestor de tareas con ArrayList<Tarea>.
 * Operaciones: agregar, listar, completar, eliminar completadas y persistencia simple.
 */
public final class GestorTareas {
    private final List<Tarea> tareas = new ArrayList<>();

    /** Agrega una tarea pendiente. */
    public void agregar(String descripcion) {
        tareas.add(new Tarea(descripcion));
    }

    /** Vista inmodificable de las tareas actuales. */
    public List<Tarea> listar() {
        return Collections.unmodifiableList(tareas);
    }

    /** Marca como completada por indice 1..n (como ve el usuario). */
    public boolean marcarComoCompletada(int indice1) {
        int i = indice1 - 1;
        if (i < 0 || i >= tareas.size()) return false;
        tareas.get(i).marcarComoCompletada();
        return true;
    }

    /** Elimina todas las tareas completadas y retorna cuantas fueron. */
    public int eliminarCompletadas() {
        int antes = tareas.size();
        tareas.removeIf(Tarea::estaCompletada);
        return antes - tareas.size();
    }

    /** Limpia todas las tareas (helper opcional). */
    public void limpiar() { tareas.clear(); }

    /** Guarda en texto UTF-8: ESTADO|descripcion */
    public void guardar(Path archivo) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(archivo, StandardCharsets.UTF_8)) {
            for (Tarea t : tareas) {
                bw.write(t.getEstado().name() + "|" + t.getDescripcion());
                bw.newLine();
            }
        }
    }

    /** Carga desde texto UTF-8. Ignora lineas invalidas. */
    public void cargar(Path archivo) throws IOException {
        tareas.clear();
        if (!Files.exists(archivo)) return;
        try (BufferedReader br = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                String s = line.trim();
                if (s.isEmpty()) continue;
                String[] parts = s.split("\\|", 2);
                if (parts.length < 2) continue;
                String estadoStr = parts[0].trim();
                String desc = parts[1].trim();
                if (desc.isEmpty()) continue;
                Tarea t = new Tarea(desc);
                if ("COMPLETADA".equalsIgnoreCase(estadoStr)) t.marcarComoCompletada();
                tareas.add(t);
            }
        }
    }
}
