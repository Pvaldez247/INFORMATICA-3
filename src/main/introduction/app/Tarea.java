package introduction.app;

import java.util.Objects;

/**
 * Representa una tarea personal con descripcion y estado.
 * Invariante: descripcion no nula ni vacia (trim) y estado no nulo.
 */
public final class Tarea {
    private final String descripcion;
    private Estado estado;

    /**
     * Crea una tarea en estado PENDIENTE.
     * @param descripcion texto no vacio; se aplica trim
     * @throws IllegalArgumentException si la descripcion es nula o vacia
     */
    public Tarea(String descripcion) {
        String d = (descripcion == null) ? "" : descripcion.trim();
        if (d.isEmpty()) throw new IllegalArgumentException("La descripcion no puede ser vacia");
        this.descripcion = d;
        this.estado = Estado.PENDIENTE;
    }

    public String getDescripcion() { return descripcion; }
    public Estado getEstado() { return estado; }
    public boolean estaCompletada() { return estado == Estado.COMPLETADA; }

    /** Marca la tarea como completada (idempotente). */
    public void marcarComoCompletada() { this.estado = Estado.COMPLETADA; }

    @Override public String toString() { return "[" + estado + "] " + descripcion; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tarea)) return false;
        Tarea t = (Tarea) o;
        return descripcion.equalsIgnoreCase(t.descripcion);
    }

    @Override public int hashCode() { return Objects.hash(descripcion.toLowerCase()); }
}
