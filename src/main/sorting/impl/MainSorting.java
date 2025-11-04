package sorting.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import sorting.app.ArrayGenerators;
import sorting.app.SortOrder;
import sorting.app.SortStats;
import sorting.app.Sorting;

public final class MainSorting {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean run = true;
            while (run) {
            menu();
            String op = sc.nextLine().trim();
            try {
                switch (op) {
                    case "1" -> runInsertion(sc);
                    case "2" -> runSelection(sc);
                    case "3" -> runMerge(sc);
                    case "4" -> runQuick(sc);
                    case "5" -> runCounting(sc);
                    case "0" -> run = false;
                    default -> System.out.println("opcion invalida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
            }
            System.out.println("Chau!");
        }
    }

    private static void menu() {
        System.out.println("=== Sorting Demo ===");
        System.out.println("1) insertionSort   2) selectionSort");
        System.out.println("3) mergeSort       4) quickSort");
        System.out.println("5) countingSort (int)   0) salir");
        System.out.print("opcion: ");
    }

    private static SortOrder askOrder(Scanner sc) {
        System.out.print("orden (A=asc, D=desc): ");
        String s = sc.nextLine().trim().toUpperCase();
        return s.startsWith("D") ? SortOrder.DESCENDING : SortOrder.ASCENDING;
    }

    private static int askSize(Scanner sc) {
        System.out.print("tamaño del arreglo (ej. 15): ");
        return Integer.parseInt(sc.nextLine().trim());
    }

    private static void runInsertion(Scanner sc) {
        int n = askSize(sc);
        Integer[] a = ArrayGenerators.randomIntegerArray(n, 0, 99);
        SortOrder order = askOrder(sc);
        System.out.println("antes:  " + Arrays.toString(a));
        SortStats st = new SortStats();
        Comparator<Integer> cmp = order == SortOrder.ASCENDING ? Comparator.naturalOrder() : Comparator.reverseOrder();
        Sorting.insertionSort(a, cmp, st);
        System.out.println("despues:" + Arrays.toString(a));
        System.out.println(st);
    }

    private static void runSelection(Scanner sc) {
        int n = askSize(sc);
        Integer[] a = ArrayGenerators.randomIntegerArray(n, 0, 99);
        SortOrder order = askOrder(sc);
        System.out.println("antes:  " + Arrays.toString(a));
        SortStats st = new SortStats();
        Comparator<Integer> cmp = order == SortOrder.ASCENDING ? Comparator.naturalOrder() : Comparator.reverseOrder();
        Sorting.selectionSort(a, cmp, st);
        System.out.println("despues:" + Arrays.toString(a));
        System.out.println(st);
    }

    private static void runMerge(Scanner sc) {
        int n = askSize(sc);
        Integer[] a = ArrayGenerators.randomIntegerArray(n, 0, 99);
        SortOrder order = askOrder(sc);
        System.out.println("antes:  " + Arrays.toString(a));
        SortStats st = new SortStats();
        Comparator<Integer> cmp = order == SortOrder.ASCENDING ? Comparator.naturalOrder() : Comparator.reverseOrder();
        Sorting.mergeSort(a, cmp, st);
        System.out.println("despues:" + Arrays.toString(a));
        System.out.println(st);
    }

    private static void runQuick(Scanner sc) {
        int n = askSize(sc);
        Integer[] a = ArrayGenerators.randomIntegerArray(n, 0, 99);
        SortOrder order = askOrder(sc);
        System.out.println("antes:  " + Arrays.toString(a));
        SortStats st = new SortStats();
        Comparator<Integer> cmp = order == SortOrder.ASCENDING ? Comparator.naturalOrder() : Comparator.reverseOrder();
        Sorting.quickSort(a, cmp, st);
        System.out.println("despues:" + Arrays.toString(a));
        System.out.println(st);
    }

    private static void runCounting(Scanner sc) {
        int n = askSize(sc);
        int[] a = ArrayGenerators.randomIntArray(n, 0, 99);
        SortOrder order = askOrder(sc);
        System.out.println("antes:  " + Arrays.toString(a));
        SortStats st = new SortStats();
        Sorting.countingSort(a, order, st);
        System.out.println("despues:" + Arrays.toString(a));
        System.out.println(st);
    }
}
