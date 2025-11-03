package integrator.impl;

import integrator.app.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public final class MainIntegrator {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager tm = new TaskManager();
        boolean run = true;
        while (run) {
            menu();
            String op = sc.nextLine().trim().toLowerCase();
            try {
                switch (op) {
                    case "1":
                        Task t = tm.create(
                                ask(sc, "description"),
                                askDate(sc, "due date (yyyy-MM-dd or empty)", true),
                                askPriority(sc));
                        System.out.println("created -> " + t);
                        break;
                    case "2":
                        System.out.print("id: ");
                        int rid = Integer.parseInt(sc.nextLine().trim());
                        System.out.println("remove -> " + tm.remove(rid));
                        break;
                    case "3":
                        System.out.print("id: ");
                        int cid = Integer.parseInt(sc.nextLine().trim());
                        System.out.println("complete -> " + tm.complete(cid));
                        break;
                    case "4":
                        System.out.print("id: ");
                        int sid = Integer.parseInt(sc.nextLine().trim());
                        LocalDate nd = askDate(sc, "new due date (yyyy-MM-dd or empty)", true);
                        System.out.println("reschedule -> " + tm.reschedule(sid, nd));
                        break;
                    case "5":
                        print(tm.listAll());
                        break;
                    case "6":
                        print(tm.listByPriority());
                        break;
                    case "7":
                        System.out.println(tm.undo());
                        break;
                    case "8":
                        System.out.println(tm.redo());
                        break;
                    case "x": run = false; break;
                    default: System.out.println("invalid option");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
        System.out.println("Chau!");
    }

    private static void menu() {
        System.out.println("=== Integrator: Task Manager ===");
        System.out.println("1) add task     2) remove task     3) complete task");
        System.out.println("4) reschedule   5) list all        6) list by priority");
        System.out.println("7) undo         8) redo            x) exit");
        System.out.print("option: ");
    }

    private static void print(List<Task> xs) {
        if (xs.isEmpty()) { System.out.println("(empty)"); return; }
        for (Task t : xs) System.out.println(t);
    }

    private static String ask(Scanner sc, String label) {
        System.out.print(label + ": ");
        return sc.nextLine();
    }

    private static LocalDate askDate(Scanner sc, String label, boolean allowEmpty) {
        System.out.print(label + ": ");
        String s = sc.nextLine().trim();
        if (allowEmpty && s.isEmpty()) return null;
        try { return LocalDate.parse(s, FMT); }
        catch (DateTimeParseException ex){ throw new IllegalArgumentException("use yyyy-MM-dd"); }
    }

    private static TaskPriority askPriority(Scanner sc) {
        System.out.print("priority (L=low, M=med, H=high, U=urgent): ");
        String s = sc.nextLine().trim().toUpperCase();
        if (s.startsWith("U")) return TaskPriority.URGENT;
        if (s.startsWith("H")) return TaskPriority.HIGH;
        if (s.startsWith("L")) return TaskPriority.LOW;
        return TaskPriority.MEDIUM;
    }
}
