# Lab 9 — Integrator (Java 21)
# Práctico 9 — Integrador (Java 21)

---

## EN — Summary
Console **Task Manager** integrating previous data structures:
- `SinglyLinkedList` (storage & iteration)
- `AVL` (index by id)
- `BinaryHeap` (priority scheduling)
- `ArrayStack` (undo/redo)
- `ArrayQueue` (available to extend)

**Features**
- Add / Remove / Complete / Reschedule tasks
- List all tasks
- List by priority (URGENT > HIGH > MEDIUM > LOW, then earlier due date, then lower id)
- Undo / Redo (Command Pattern)

**Packages & classes**
- `integrator.app`: Domain (`Task`, `TaskPriority`, `TaskState`), DS (`SinglyLinkedList`, `AvlTree`, `BinaryHeap`, `ArrayStack`, `ArrayQueue`), Commands (`Command`, `AddTask`, `RemoveTask`, `CompleteTask`, `RescheduleTask`), Service (`TaskManager`)
- `integrator.impl`: `MainIntegrator`

**Run (inside `practico9-integrador/`)**
```bash
mkdir -p out
javac --release 21 -encoding UTF-8 -d out app/*.java impl/MainIntegrator.java
java -cp out integrator.impl.MainIntegrator
```

---

## ES — Resumen
**Gestor de Tareas** de consola que integra **lista enlazada simple**, **árbol AVL**, **montículo binario**, **pila (undo/redo)** y **cola** (extensible).

**Operaciones**
- Crear / Eliminar / Completar / Reprogramar tareas
- Listar todas las tareas
- Listar por prioridad (URGENTE > ALTA > MEDIA > BAJA; a igualdad, fecha e id)
- Deshacer / Rehacer (patrón Command)

**Estructura**
```
practico9-integrador/
  app/
    ArrayQueue.java
    ArrayStack.java
    AvlTree.java
    BinaryHeap.java
    Command.java
    Commands.java
    SinglyLinkedList.java
    Task.java
    TaskPriority.java
    TaskState.java
  impl/
    MainIntegrator.java
  README.md
```
