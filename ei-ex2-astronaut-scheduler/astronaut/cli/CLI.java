package com.jitesh.astronaut.cli;

import com.jitesh.astronaut.core.ScheduleManager;
import com.jitesh.astronaut.factory.TaskFactory;
import com.jitesh.astronaut.model.Task;
import com.jitesh.astronaut.observer.Observer;
import com.jitesh.astronaut.persistence.JsonPersistence;

import java.io.File;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CLI {
    // Using singleton manager (not 100% a fan of singletons, but sticking with it for now)
    private final ScheduleManager manager = ScheduleManager.getInstance();
    private final Scanner input = new Scanner(System.in);   // renamed for clarity

    public CLI() {
        // register a simple observer – could swap for lambda, but this is explicit
        manager.registerObserver(new Observer() {
            @Override
            public void update(String message) {
                System.out.println("[NOTIFY] " + message);
            }
        });
    }

    public void start() {
        System.out.println("=== Astronaut Scheduler CLI ===");
        System.out.println("Available commands: add | list | remove | complete | save | load | exit");

        // Main REPL loop
        while (true) {
            System.out.print("> ");
            String cmd = input.nextLine().trim();   // renamed line -> cmd for readability

            if (cmd.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                if (cmd.equalsIgnoreCase("add")) {
                    System.out.print("Description: ");
                    String desc = input.nextLine();

                    System.out.print("Start (HH:mm): ");
                    String startStr = input.nextLine();

                    System.out.print("End (HH:mm): ");
                    String endStr = input.nextLine();

                    Task task = TaskFactory.create(desc, startStr, endStr);
                    boolean added = manager.addTask(task);
                    if (!added) {
                        System.out.println("Could not add task (conflict or overlap?).");
                    }

                } else if (cmd.equalsIgnoreCase("list")) {
                    // just dumping tasks, might format nicer later
                    manager.listTasks().forEach(System.out::println);

                } else if (cmd.startsWith("remove")) {
                    System.out.print("Task ID: ");
                    String id = input.nextLine();
                    if (!manager.removeTask(id)) {
                        System.out.println("Task not found.");
                    }

                } else if (cmd.equalsIgnoreCase("complete")) {
                    System.out.print("Task ID: ");
                    String id = input.nextLine();
                    manager.markCompleted(id);

                } else if (cmd.equalsIgnoreCase("save")) {
                    System.out.print("Filename: ");
                    String filename = input.nextLine();
                    try {
                        JsonPersistence.save(manager.listTasks(), new File(filename));
                        System.out.println("Saved to " + filename);
                    } catch (Exception ex) {
                        // maybe should log instead of just printing?
                        System.out.println("Save failed: " + ex.getMessage());
                    }

                } else if (cmd.equalsIgnoreCase("load")) {
                    System.out.print("Filename: ");
                    String filename = input.nextLine();
                    try {
                        Task[] tasks = JsonPersistence.load(new File(filename));
                        for (Task t : tasks) {
                            manager.addTask(t);  // might duplicate, maybe clear first?
                        }
                        System.out.println("Loaded " + tasks.length + " tasks.");
                    } catch (Exception ex) {
                        System.out.println("Load failed: " + ex.getMessage());
                    }

                } else {
                    System.out.println("Unknown command. Type 'list' or 'exit' maybe?");
                }

            } catch (DateTimeParseException dte) {
                System.out.println("Invalid time format. Please use HH:mm (24hr).");
            } catch (Exception e) {
                // overly generic catch, but prevents CLI from crashing
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Exiting... bye!");
    }
}
