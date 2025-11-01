# Lab 3 — Sorting (Java 21)
# Práctico 3 — Ordenamientos (Java 21)

---

## EN — Summary
Console demo comparing classic sorting algorithms with basic statistics.

**Algorithms**
- insertionSort — stable, in-place
- selectionSort — in-place, not stable
- mergeSort — stable, O(n log n)
- quickSort — in-place, fast average case
- countingSort (int) — O(n + k), stable

**Packages & classes**
- sorting.app: SortOrder, SortStats, ArrayGenerators, Sorting
- sorting.impl: MainSorting (console menu)

**How to run (inside `practico3-sorting/`)**
```bash
mkdir -p out
javac --release 21 -encoding UTF-8 -d out   app/SortOrder.java app/SortStats.java app/ArrayGenerators.java app/Sorting.java   impl/MainSorting.java
java -cp out sorting.impl.MainSorting
```

---

## ES — Resumen
Demostración en consola de ordenamientos clásicos y comparación con estadísticas sencillas.

**Algoritmos**
- insertionSort — estable, in-place
- selectionSort — in-place, no estable
- mergeSort — estable, O(n log n)
- quickSort — in-place, promedio rápido
- countingSort (int) — O(n + k), estable

**Ejecutar (dentro de `practico3-sorting/`)**
```bash
mkdir -p out
javac --release 21 -encoding UTF-8 -d out   app/SortOrder.java app/SortStats.java app/ArrayGenerators.java app/Sorting.java   impl/MainSorting.java
java -cp out sorting.impl.MainSorting
```
