package avl.impl;

import avl.app.AvlTree;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** Console demo for AvlTree<Integer>. */
public final class MainAvl {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AvlTree<Integer> avl = new AvlTree<>();
        boolean run = true;
        while (run) {
            menu(avl);
            String op = sc.nextLine().trim().toLowerCase();
            try {
                switch (op) {
                    case "a": // insert one
                        System.out.print("valor (int): ");
                        avl.insert(Integer.parseInt(sc.nextLine().trim()));
                        break;
                    case "b": // insert many
                        System.out.print("valores (espacios o comas): ");
                        for (String tok : sc.nextLine().trim().replace(","," ").split("\\s+")) {
                            if (!tok.isEmpty()) avl.insert(Integer.parseInt(tok));
                        }
                        break;
                    case "c": // remove
                        System.out.print("valor (int): ");
                        System.out.println("remove -> " + avl.remove(Integer.parseInt(sc.nextLine().trim())));
                        break;
                    case "d": // contains
                        System.out.print("valor (int): ");
                        System.out.println("contains -> " + avl.contains(Integer.parseInt(sc.nextLine().trim())));
                        break;
                    case "e": // traversals
                        System.out.println("inorder     = " + avl.inorder());
                        System.out.println("preorder    = " + avl.preorder());
                        System.out.println("postorder   = " + avl.postorder());
                        System.out.println("level-order = " + avl.levelOrder());
                        break;
                    case "f": // min/max
                        System.out.println("min = " + avl.min() + ", max = " + avl.max());
                        break;
                    case "g": // clear
                        avl.clear();
                        System.out.println("cleared");
                        break;
                    case "x":
                        run = false; break;
                    default:
                        System.out.println("opcion invalida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
        System.out.println("Chau!");
    }

    private static void menu(AvlTree<Integer> avl) {
        System.out.println("=== AVL Tree Demo (Integer) ===   size=" + avl.size());
        System.out.println("a)insert  b)insert many  c)remove  d)contains");
        System.out.println("e)traversals           f)min/max   g)clear");
        System.out.println("x)salir");
        System.out.print("opcion: ");
    }
}
