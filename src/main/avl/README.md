# Lab 6 — AVL Tree (Java 21)
# Práctico 6 — Árbol AVL (Java 21)

---

## EN — Summary
Generic **AVL (self-balancing) Binary Search Tree** for `Comparable` keys.  
Operations keep the tree height in O(log n) using LL/LR/RR/RL rotations.

**Packages & classes**
- `avl.app`: `AvlTree<T extends Comparable<? super T>>`
- `avl.impl`: `MainAvl` (console menu with `Integer`)

**Main operations**
- `insert(key)` — ignores duplicates
- `remove(key)` — returns `true` if removed
- `contains(key)`
- `min()`, `max()` — return smallest/largest (or `null` if empty)
- Traversals: `inorder()`, `preorder()`, `postorder()`, `levelOrder()`
- `size()`, `isEmpty()`, `clear()`

**How to run (inside `practico6-avl/`)**
```bash
mkdir -p out
javac --release 21 -encoding UTF-8 -d out   app/AvlTree.java   impl/MainAvl.java
java -cp out avl.impl.MainAvl
```

---

## ES — Resumen
**Árbol AVL** genérico (BST auto-balanceado) para claves `Comparable`.

**Operaciones**
- Inserción, eliminación, búsqueda (`insert`, `remove`, `contains`)
- Mínimo y máximo (`min`, `max`)
- Recorridos: inorden, preorden, postorden, por niveles
- Tamaño/estado: `size`, `isEmpty`, `clear`

**Complejidad**
- Búsqueda/inserción/eliminación: **O(log n)** (rebalanceo por rotaciones)
- Recorridos: **O(n)**

**Estructura del folder**
```
practico6-avl/
  app/
    AvlTree.java
  impl/
    MainAvl.java
  README.md
```

**Notas**
- Requiere Java 21 (ajustá `--release` si usás otra versión).
- La demo usa `Integer`, pero el árbol acepta cualquier tipo `Comparable`.
