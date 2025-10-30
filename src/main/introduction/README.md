# Lab 1 — Introduction: Task Manager (Java 21)
# Práctico 1 — Introducción: Gestor de Tareas (Java 21)

---

## EN — Summary
Console-based personal task manager.

**Features**
- Add tasks (validates non-empty description).
- List all tasks.
- Mark task as **COMPLETED** (idempotent).
- Delete all **COMPLETED** tasks.
- *(Extra)* Save/Load from a UTF-8 text file `tareas.txt`.

**Packages & classes**
- `introduction.app`: `Estado` (enum), `Tarea`, `GestorTareas`
- `introduction.impl`: `Main` (console menu)

**Run instructions**

> From the **repository root** (not inside `src`)

**VS Code**
1) Make sure `src/main/java` is in the Java Source Path  
   (Explorer → right-click `src/main/java` → **Add Folder to Java Source Path**).
2) Open `src/main/java/introduction/impl/Main.java` → click **Run** above `main`.

**Terminal (Git Bash / PowerShell)**
```bash
# create output dir
mkdir -p out

# compile (Java 21, UTF-8)
javac --release 21 -encoding UTF-8 -d out \
  src/main/java/introduction/app/*.java \
  src/main/java/introduction/impl/Main.java

# run
java -cp out introduction.impl.Main
