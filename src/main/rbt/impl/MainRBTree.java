package rbt.impl;

import java.util.Scanner;
import rbt.app.RedBlackTree;

public final class MainRBTree {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            RedBlackTree<Integer> rbt = new RedBlackTree<>();
        boolean run = true;
        while (run) {
            menu(rbt);
            String op = sc.nextLine().trim().toLowerCase();
            try {
                switch (op) {
                    case "a" -> {
                        System.out.print("valor (int): ");
                        rbt.insert(Integer.valueOf(sc.nextLine().trim()));
                    }
                    case "b" -> {
                        System.out.print("valores (espacios o comas): ");
                        String line = sc.nextLine().trim().replace(",", " ");
                        for (String tok : line.split("\\s+")) if (!tok.isEmpty()) rbt.insert(Integer.valueOf(tok));
                    }
                    case "c" -> {
                        System.out.print("valor a eliminar: ");
                        System.out.println("remove -> " + rbt.remove(Integer.valueOf(sc.nextLine().trim())));
                    }
                    case "d" -> {
                        rbt.deleteMin();
                        System.out.println("deleteMin OK");
                    }
                    case "e" -> {
                        rbt.deleteMax();
                        System.out.println("deleteMax OK");
                    }
                    case "f" -> {
                        System.out.print("buscar valor: ");
                        System.out.println("contains -> " + rbt.contains(Integer.valueOf(sc.nextLine().trim())));
                    }
                    case "g" -> System.out.println("min=" + rbt.min() + ", max=" + rbt.max());
                    case "h" -> {
                        System.out.println("inorder     = " + rbt.inorder());
                        System.out.println("preorder    = " + rbt.preorder());
                        System.out.println("postorder   = " + rbt.postorder());
                        System.out.println("level-order = " + rbt.levelOrder());
                    }
                    case "i" -> {
                        rbt.clear();
                        System.out.println("cleared");
                    }
                    case "x" -> run = false;
                    default -> System.out.println("opcion invalida");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
            }
            System.out.println("Chau!");
        }
    }

    private static void menu(RedBlackTree<Integer> rbt) {
        System.out.println("=== Red-Black Tree Demo (Integer) ===   size=" + rbt.inorder().size());
        System.out.println("a)insert  b)insert many  c)remove  d)deleteMin  e)deleteMax");
        System.out.println("f)contains  g)min/max  h)traversals  i)clear");
        System.out.println("x)salir");
        System.out.print("opcion: ");
    }
}
