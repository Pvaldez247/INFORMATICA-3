package linkedlist.impl;

import linkedlist.app.SinglyLinkedList;

import java.util.Arrays;
import java.util.Scanner;

public final class MainLinkedList {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            SinglyLinkedList<String> list = new SinglyLinkedList<>();
        boolean run = true;
        while (run) {
            menu(list);
            String op = sc.nextLine().trim().toLowerCase();
            try {
                switch (op) {
                    case "a" -> {
                        System.out.print("valor: ");
                        list.addFirst(sc.nextLine());
                    }
                    case "b" -> {
                        System.out.print("valor: ");
                        list.addLast(sc.nextLine());
                    }
                    case "c" -> System.out.println("removeFirst -> " + list.removeFirst());
                    case "d" -> System.out.println("removeLast  -> " + list.removeLast());
                    case "e" -> {
                        System.out.print("index (0..size): ");
                        int ie = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("valor: ");
                        list.insert(ie, sc.nextLine());
                    }
                    case "f" -> {
                        System.out.print("index (0..size-1): ");
                        int ir = Integer.parseInt(sc.nextLine().trim());
                        System.out.println("removeAt -> " + list.removeAt(ir));
                    }
                    case "g" -> {
                        System.out.print("index (0..size-1): ");
                        int ig = Integer.parseInt(sc.nextLine().trim());
                        System.out.println("get -> " + list.get(ig));
                    }
                    case "h" -> {
                        System.out.print("index (0..size-1): ");
                        int is = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("nuevo valor: ");
                        System.out.println("set -> old=" + list.set(is, sc.nextLine()));
                    }
                    case "i" -> {
                        System.out.print("valor: ");
                        System.out.println("contains -> " + list.contains(sc.nextLine()));
                    }
                    case "j" -> {
                        list.reverse();
                        System.out.println("reversed.");
                    }
                    case "k" -> {
                        list.clear();
                        System.out.println("cleared.");
                    }
                    case "l" -> System.out.println("toArray -> " + Arrays.toString(list.toArray()));
                    case "x" -> run = false;
                    default -> System.out.println("opcion invalida");
                }
            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
            }
            System.out.println("Chau!");
        }
    }

    private static void menu(SinglyLinkedList<String> list) {
        System.out.println("=== Single Linked List Demo ===   size=" + list.size());
        System.out.println(list);
        System.out.println("a)addFirst b)addLast c)removeFirst d)removeLast");
        System.out.println("e)insert   f)removeAt g)get         h)set");
        System.out.println("i)contains j)reverse  k)clear       l)toArray");
        System.out.println("x)salir");
        System.out.print("opcion: ");
    }
}
