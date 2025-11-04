package heap.impl;

import heap.app.BinaryHeap;
import heap.app.HeapType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public final class MainHeap {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            HeapType type = askType(sc);
            BinaryHeap<Integer> heap = BinaryHeap.ofType(type);

            boolean run = true;
            while (run) {
                menu(type, heap);
                String op = sc.nextLine().trim().toLowerCase();
                try {
                    switch (op) {
                    case "a" -> {
                        System.out.print("valor (int): ");
                        heap.insert(Integer.valueOf(sc.nextLine().trim()));
                    }
                    case "b" -> {
                        System.out.print("valores (espacios o comas): ");
                        for (String tok : sc.nextLine().trim().replace(",", " ").split("\\s+")) {
                            if (!tok.isEmpty()) heap.insert(Integer.valueOf(tok));
                        }
                    }
                    case "c" -> System.out.println("peek -> " + heap.peek());
                    case "d" -> System.out.println("poll -> " + heap.poll());
                    case "e" -> {
                        System.out.print("valores para build (espacios o comas): ");
                        String line = sc.nextLine().trim().replace(",", " ");
                        List<Integer> vals = new ArrayList<>();
                        for (String tok : line.split("\\s+")) if (!tok.isEmpty()) vals.add(Integer.valueOf(tok));
                        heap.build(vals);
                        System.out.println("build OK");
                    }
                    case "f" -> {
                        System.out.print("index (0..size-1): ");
                        int idx = Integer.parseInt(sc.nextLine().trim());
                        System.out.println("removeAt -> " + heap.removeAt(idx));
                    }
                    case "g" -> System.out.println("heap-array = " + heap.snapshot());
                    case "h" -> {
                        heap.clear(); 
                        System.out.println("cleared.");
                    }
                    case "x" -> run = false;
                    default -> System.out.println("opcion invalida");
                }                } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                System.out.println();
            }
            System.out.println("Chau!");
        }
    }

    private static HeapType askType(Scanner sc) {
        System.out.print("Tipo de heap (M=min, X=max): ");
        String s = sc.nextLine().trim().toUpperCase();
        return s.startsWith("X") ? HeapType.MAX : HeapType.MIN;
    }

    private static void menu(HeapType type, BinaryHeap<Integer> heap) {
        System.out.println("=== Binary Heap Demo (" + type + ") ===   size=" + heap.snapshot().size());
        System.out.println("a)insert  b)insert many  c)peek  d)poll  e)build  f)removeAt  g)snapshot  h)clear");
        System.out.println("x)salir");
        System.out.print("opcion: ");
    }
}
