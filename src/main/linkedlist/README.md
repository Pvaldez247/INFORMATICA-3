# Lab 5 — Single Linked List (Java 21)
# Práctico 5 — Lista Enlazada Simple (Java 21)

---

## EN — Summary
Generic singly linked list (head/tail) with common operations and a console demo.

**Packages & classes**
- `linkedlist.app`: `SinglyLinkedList<T>`
- `linkedlist.impl`: `MainLinkedList` (console menu)

**How to run (inside `practico5-linkedlist/`)**
```bash
mkdir -p out
javac --release 21 -encoding UTF-8 -d out   app/SinglyLinkedList.java   impl/MainLinkedList.java
java -cp out linkedlist.impl.MainLinkedList
```

---

## ES — Resumen
Lista **enlazada simple genérica** con `head` y `tail` y operaciones típicas. Incluye menú de prueba.

**Operaciones**
- Inserción: `addFirst`, `addLast`, `insert(index, value)`
- Eliminación: `removeFirst`, `removeLast`, `removeAt(index)`, `removeValue(value)`
- Acceso/edición: `get(index)`, `set(index, value)`
- Búsqueda/utilidades: `contains`, `indexOf`, `reverse`, `clear`, `toArray`, `size`, `isEmpty`
- Iteración: soporta `for-each` (implementa `Iterable<T>`)

**Complejidad**
- O(1): `addFirst`, `addLast`, `removeFirst`
- O(n): `removeLast`, `get/set`, `insert`, `removeAt`, `indexOf`, `reverse`, `toArray`

**Estructura**
```
practico5-linkedlist/
  app/
    SinglyLinkedList.java
  impl/
    MainLinkedList.java
  README.md
```

**Notas**
- Requiere Java 21 (ajusta `--release` si es necesario).
