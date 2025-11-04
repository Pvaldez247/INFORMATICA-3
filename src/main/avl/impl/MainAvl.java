package avl.impl;

import avl.app.AvlTree;

import java.util.Scanner;


public final class MainAvl {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            AvlTree<Integer> avl = new AvlTree<>();
            boolean run = true;
            while (run) {
                menu(avl);
                String op = sc.nextLine().trim().toLowerCase();
                try {
                    switch (op) {
                    case "a" -> {
                        System.out.print("valor (int): ");
                        avl.insert(Integer.valueOf(sc.nextLine().trim()));
                    }
                    case "b" -> {
                        System.out.print("valores (espacios o comas): ");
                        for (String tok : sc.nextLine().trim().replace(","," ").split("\\s+")) {
                            if (!tok.isEmpty()) avl.insert(Integer.valueOf(tok));
                        }
                    }
                    case "c" -> {
                        System.out.print("valor (int): ");
                        System.out.println("remove -> " + avl.remove(Integer.valueOf(sc.nextLine().trim())));
                    }
                    case "d" -> {
                        System.out.print("valor (int): ");
                        System.out.println("contains -> " + avl.contains(Integer.valueOf(sc.nextLine().trim())));
                    }
                    case "e" -> {
                        System.out.println("inorder     = " + avl.inorder());
                        System.out.println("preorder    = " + avl.preorder());
                        System.out.println("postorder   = " + avl.postorder());
                        System.out.println("level-order = " + avl.levelOrder());
                    }
                    case "f" -> System.out.println("min = " + avl.min() + ", max = " + avl.max());
                    case "g" -> {
                        avl.clear();
                        System.out.println("cleared");
                    }
                    case "x" -> run = false;
                    default -> System.out.println("opcion invalida");
                }                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                System.out.println();
            }
            System.out.println("Chau!");
        }
    }

    private static void menu(AvlTree<Integer> avl) {
        System.out.println("=== AVL Tree Demo (Integer) ===   size=" + avl.size());
        System.out.println("a)insert  b)insert many  c)remove  d)contains");
        System.out.println("e)traversals           f)min/max   g)clear");
        System.out.println("x)salir");
        System.out.print("opcion: ");
    }
}
