package heap.impl;

import heap.app.BinaryHeap;
import heap.app.HeapType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** Console demo for BinaryHeap<Integer>. */
public final class MainHeap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HeapType type = askType(sc);
        BinaryHeap<Integer> heap = BinaryHeap.ofType(type);

        boolean run = true;
        while (run) {
            menu(type, heap);
            String op = sc.nextLine().trim().toLowerCase();
            try {
                switch (op) {
                    case "a": // insert
                        System.out.print("valor (int): ");
                        heap.insert(Integer.parseInt(sc.nextLine().trim()));
                        break;
                    case "b": // insert many
                        System.out.print("valores (espacios o comas): ");
                        for (String tok : sc.nextLine().trim().replace(",", " ").split("\\s+")) {
                            if (!tok.isEmpty()) heap.insert(Integer.parseInt(tok));
                        }
                        break;
                    case "c": // peek
                        System.out.println("peek -> " + heap.peek());
                        break;
                    case "d": // poll
                        System.out.println("poll -> " + heap.poll());
                        break;
                    case "e": // build from list (replace)
                        System.out.print("valores para build (espacios o comas): ");
                        String line = sc.nextLine().trim().replace(",", " ");
                        List<Integer> vals = new ArrayList<>();
                        for (String tok : line.split("\\s+")) if (!tok.isEmpty()) vals.add(Integer.parseInt(tok));
                        heap.build(vals);
                        System.out.println("build OK");
                        break;
                    case "f": // removeAt
                        System.out.print("index (0..size-1): ");
                        int idx = Integer.parseInt(sc.nextLine().trim());
                        System.out.println("removeAt -> " + heap.removeAt(idx));
                        break;
                    case "g": // snapshot
                        System.out.println("heap-array = " + heap.snapshot());
                        break;
                    case "h": // clear
                        heap.clear(); System.out.println("cleared.");
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
