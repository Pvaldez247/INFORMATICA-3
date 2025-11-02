# Lab 7 — Red-Black Tree (Java 21)
# Práctico 7 — Árbol Rojo-Negro (Java 21)

---

## EN — Summary
Left-Leaning **Red-Black Tree (LLRB)** for `Comparable` keys. Balanced BST with `O(log n)` search/insert/delete.

**Packages & classes**
- `rbtree.app`: `RedBlackTree<T extends Comparable<? super T>>`
- `rbtree.impl`: `MainRBTree` (console menu with `Integer`)

**Operations**
- `insert(key)` — adds key (duplicates ignored)
- `remove(key)` — deletes key (if present)
- `deleteMin()`, `deleteMax()`
- `contains(key)`, `min()`, `max()`
- Traversals: `inorder()`, `preorder()`, `postorder()`, `levelOrder()`
- `clear()`, `size()`, `isEmpty()`

**Run (inside `practico7-redblack/`)**
```bash
mkdir -p out
javac --release 21 -encoding UTF-8 -d out   app/RedBlackTree.java   impl/MainRBTree.java
java -cp out rbtree.impl.MainRBTree
```

---

## ES — Resumen
**Árbol Rojo-Negro** (Left-Leaning) genérico para claves `Comparable` con búsqueda, inserción y eliminación en **O(log n)**.

**Notas de diseño**
- Implementación **LLRB** (2‑3 tree): rotaciones izquierda/derecha, `flipColors`, y helpers `moveRedLeft/Right`, `balance`.
- Sin duplicados: una clave repetida no modifica el árbol.
- `size` por nodo para consultas de tamaño.

**Estructura**
```
practico7-redblack/
  app/
    RedBlackTree.java
  impl/
    MainRBTree.java
  README.md
```

**Tip para la demo**
- Probá: insertar varios valores, ver `inorder` (debe estar ordenado), eliminar claves particulares y extremos (`deleteMin/deleteMax`) y chequear equilibrio con los recorridos.
