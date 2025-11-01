package recursion.impl;

import recursion.app.RecursionUtils;

import java.util.List;
import java.util.Scanner;

public final class MainRecursion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        while (run) {
            menu();
            String op = sc.nextLine().trim();
            try {
                switch (op) {
                    case "1":
                        System.out.print("n (>=0): ");
                        int n = readInt(sc);
                        System.out.println("factorial(" + n + ") = " + RecursionUtils.factorial(n));
                        break;
                    case "2":
                        System.out.print("n (>=0): ");
                        int f = readInt(sc);
                        System.out.println("fibonacci(" + f + ") = " + RecursionUtils.fibonacci(f));
                        break;
                    case "3":
                        System.out.print("base: ");
                        long base = readLong(sc);
                        System.out.print("exp (>=0): ");
                        int exp = readInt(sc);
                        System.out.println(base + "^" + exp + " = " + RecursionUtils.power(base, exp));
                        break;
                    case "4":
                        System.out.print("texto: ");
                        String s = sc.nextLine();
                        System.out.println("reverse = " + RecursionUtils.reverse(s));
                        break;
                    case "5":
                        System.out.print("texto: ");
                        String p = sc.nextLine();
                        System.out.println("isPalindrome = " + RecursionUtils.isPalindrome(p));
                        break;
                    case "6":
                        int[] arr = readIntArray(sc, "valores del arreglo (espacios o comas): ");
                        System.out.println("sum = " + RecursionUtils.sumArray(arr));
                        break;
                    case "7":
                        int[] sorted = readIntArray(sc, "arreglo ORDENADO ascendente: ");
                        System.out.print("clave a buscar: ");
                        int key = readInt(sc);
                        int idx = RecursionUtils.binarySearch(sorted, key);
                        System.out.println("resultado = " + idx + (idx >= 0 ? " (valor=" + sorted[idx] + ")" : " (no encontrado)"));
                        break;
                    case "8":
                        System.out.print("a: ");
                        int a = readInt(sc);
                        System.out.print("b: ");
                        int b = readInt(sc);
                        System.out.println("gcd(" + a + "," + b + ") = " + RecursionUtils.gcd(a, b));
                        break;
                    case "9":
                        System.out.print("n (>=0): ");
                        int nb = readInt(sc);
                        System.out.println("bin = " + RecursionUtils.toBinaryString(nb));
                        break;
                    case "10":
                        System.out.print("n discos (0..12 recomendado): ");
                        int nh = readInt(sc);
                        List<String> moves = RecursionUtils.hanoi(nh, 'A', 'B', 'C');
                        System.out.println("movimientos = " + moves.size());
                        int preview = Math.min(20, moves.size());
                        for (int i = 0; i < preview; i++) System.out.println((i+1) + ". " + moves.get(i));
                        if (moves.size() > preview) System.out.println("... (" + (moves.size() - preview) + " mas)");
                        break;
                    case "0":
                        run = false;
                        break;
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

    private static void menu() {
        System.out.println("=== Recursion Demo ===");
        System.out.println("1) factorial     2) fibonacci    3) power");
        System.out.println("4) reverse       5) palindrome   6) sum array");
        System.out.println("7) binarySearch  8) gcd          9) toBinaryString");
        System.out.println("10) hanoi        0) salir");
        System.out.print("opcion: ");
    }

    private static int readInt(Scanner sc) {
        String s = sc.nextLine().trim();
        return Integer.parseInt(s);
    }

    private static long readLong(Scanner sc) {
        String s = sc.nextLine().trim();
        return Long.parseLong(s);
    }

    private static int[] readIntArray(Scanner sc, String prompt) {
        System.out.print(prompt);
        String line = sc.nextLine().trim();
        if (line.isEmpty()) return new int[0];
        line = line.replace(",", " ");
        String[] parts = line.split("\s+");
        int[] out = new int[parts.length];
        for (int i = 0; i < parts.length; i++) out[i] = Integer.parseInt(parts[i]);
        return out;
    }
}
