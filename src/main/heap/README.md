# Lab 8 — Binary Heap (Java 21)
# Práctico 8 — Montículo Binario (Java 21)

---

## EN — Summary
Generic **Binary Heap** (array-based) supporting **MIN** or **MAX** behavior via comparator.

**Packages & classes**
- `heap.app`:
  - `HeapType` — `MIN` or `MAX`
  - `BinaryHeap<T>` — generic heap with `insert`, `peek`, `poll`, `removeAt`, `build`, `clear`, `size`, `isEmpty`, `snapshot`
- `heap.impl`:
  - `MainHeap` — console demo using `Integer`

**How to run (inside `practico8-heap/`)**
```bash
mkdir -p out
javac --release 21 -encoding UTF-8 -d out   app/HeapType.java app/BinaryHeap.java   impl/MainHeap.java
java -cp out heap.impl.MainHeap
```

---

## ES — Resumen
**Montículo binario** genérico con comportamiento **MIN** o **MAX** mediante comparador.

**Operaciones**
- `insert(v)`: inserta y restablece la propiedad de heap (sift-up)
- `peek()`: devuelve la raíz (mínimo o máximo) sin remover
- `poll()`: extrae la raíz (sift-down)
- `removeAt(i)`: elimina el elemento en el índice interno `i` (0..size-1)
- `build(Collection)`: construye el heap en **O(n)** desde una lista/arreglo
- `clear()`, `size()`, `isEmpty()`, `snapshot()`

**Estructura**
```
practico8-heap/
  app/
    HeapType.java
    BinaryHeap.java
  impl/
    MainHeap.java
  README.md
```

**Notas**
- Requiere Java 21 (ajustá `--release` si usás otra versión).
- `BinaryHeap.ofType(HeapType.MIN|MAX)` crea rápidamente un min-heap o max-heap.
