# Lab 4 — Stack & Queue with Arrays (Java 21)
# Práctico 4 — Pila y Cola con Arreglos (Java 21)

---

## EN — Summary
Array-based implementations of **Stack (LIFO)** and **Queue (FIFO)** with fixed capacity, plus a console demo.

**Packages & classes**
- `stackqueue.app`:
  - `Stack<T>` — basic Stack API
  - `ArrayStack<T>` — array-based stack (fixed capacity)
  - `Queue<T>` — basic Queue API
  - `ArrayQueue<T>` — circular array-based queue (fixed capacity)
- `stackqueue.impl`:
  - `MainStackQueue` — console menu demo (uses `String` elements)

**How to run (inside `practico4-stackqueue/`)**
```bash
mkdir -p out
javac --release 21 -encoding UTF-8 -d out   app/Stack.java app/ArrayStack.java app/Queue.java app/ArrayQueue.java   impl/MainStackQueue.java
java -cp out stackqueue.impl.MainStackQueue
```

---

## ES — Resumen
Implementaciones sobre **arreglos** de Pila (LIFO) y Cola (FIFO) con capacidad fija, y demo por consola.

**Características**
- **Pila**: `push`, `pop`, `peek`, `isEmpty`, `isFull`, `size`, `capacity`.
- **Cola** (circular): `enqueue`, `dequeue`, `peek`, `isEmpty`, `isFull`, `size`, `capacity`.
- Excepciones con mensajes claros en overflow/underflow.

**Estructura del folder**
```
practico4-stackqueue/
  app/
    Stack.java
    ArrayStack.java
    Queue.java
    ArrayQueue.java
  impl/
    MainStackQueue.java
  README.md
```

**Ejecutar (dentro de `practico4-stackqueue/`)**
```bash
mkdir -p out
javac --release 21 -encoding UTF-8 -d out   app/Stack.java app/ArrayStack.java app/Queue.java app/ArrayQueue.java   impl/MainStackQueue.java
java -cp out stackqueue.impl.MainStackQueue
```

**Notas**
- Requiere Java 21 (ajusta `--release` si usas otra versión).
- Las estructuras son genéricas (`<T>`); la demo usa `String` para simplificar.
