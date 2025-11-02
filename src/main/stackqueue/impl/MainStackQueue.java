package stackqueue.impl;

import stackqueue.app.ArrayQueue;
import stackqueue.app.ArrayStack;
import java.util.Scanner;

public final class MainStackQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("=== Stack & Queue (arrays) ===");
            System.out.println("1) Demo Stack<String>");
            System.out.println("2) Demo Queue<String>");
            System.out.println("0) Salir");
            System.out.print("Opcion: ");
            String op = sc.nextLine().trim();
            System.out.println();
            try {
                switch (op) {
                    case "1": demoStack(sc); break;
                    case "2": demoQueue(sc); break;
                    case "0": run = false; break;
                    default: System.out.println("Opcion invalida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
        System.out.println("Chau!");
    }

    private static void demoStack(Scanner sc) {
        System.out.print("Capacidad de la pila: ");
        int cap = Integer.parseInt(sc.nextLine().trim());
        ArrayStack<String> st = new ArrayStack<>(cap);
        boolean loop = true;
        while (loop) {
            System.out.println("Stack " + st + "  (size=" + st.size() + "/" + st.capacity() + ")");
            System.out.println("a) push   b) pop   c) peek   d) estado   x) salir");
            System.out.print("opcion: ");
            String op = sc.nextLine().trim().toLowerCase();
            switch (op) {
                case "a":
                    System.out.print("valor: ");
                    st.push(sc.nextLine());
                    break;
                case "b":
                    System.out.println("pop -> " + st.pop());
                    break;
                case "c":
                    System.out.println("peek -> " + st.peek());
                    break;
                case "d":
                    System.out.println("empty=" + st.isEmpty() + ", full=" + st.isFull());
                    break;
                case "x":
                    loop = false; break;
                default:
                    System.out.println("opcion invalida");
            }
        }
    }

    private static void demoQueue(Scanner sc) {
        System.out.print("Capacidad de la cola: ");
        int cap = Integer.parseInt(sc.nextLine().trim());
        ArrayQueue<String> q = new ArrayQueue<>(cap);
        boolean loop = true;
        while (loop) {
            System.out.println("Queue " + q + "  (size=" + q.size() + "/" + q.capacity() + ")");
            System.out.println("a) enqueue   b) dequeue   c) peek   d) estado   x) salir");
            System.out.print("opcion: ");
            String op = sc.nextLine().trim().toLowerCase();
            switch (op) {
                case "a":
                    System.out.print("valor: ");
                    q.enqueue(sc.nextLine());
                    break;
                case "b":
                    System.out.println("dequeue -> " + q.dequeue());
                    break;
                case "c":
                    System.out.println("peek -> " + q.peek());
                    break;
                case "d":
                    System.out.println("empty=" + q.isEmpty() + ", full=" + q.isFull());
                    break;
                case "x":
                    loop = false; break;
                default:
                    System.out.println("opcion invalida");
            }
        }
    }
}
