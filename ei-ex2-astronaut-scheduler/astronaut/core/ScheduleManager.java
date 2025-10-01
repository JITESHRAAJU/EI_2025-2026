package com.jitesh.astronaut.core;

import com.jitesh.astronaut.model.Task;
import com.jitesh.astronaut.observer.Notifier;

import java.util.*;

public class ScheduleManager {
    // Singleton (kinda overkill here, but keeping it simple for now)
    private static ScheduleManager instance;

    // internal state
    private final List<Task> tasks = new ArrayList<>();
    private final Notifier notifier = new Notifier();

    private ScheduleManager() {
        // nothing special here, but we could pre-load tasks if needed
    }

    public static synchronized ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    // useful for tests – basically nukes the singleton
    public static synchronized void resetForTests() {
        instance = null;
    }

    public void registerObserver(com.jitesh.astronaut.observer.Observer o) {
        notifier.register(o);
    }

    // Adds a new task to the schedule (returns false if there's an overlap)
    public boolean addTask(Task newTask) {
        Objects.requireNonNull(newTask, "Task cannot be null");

        // check for overlap – quick and dirty
        for (Task existing : tasks) {
            boolean overlaps = newTask.getStart().isBefore(existing.getEnd()) &&
                               newTask.getEnd().isAfter(existing.getStart());

            if (overlaps) {
                notifier.notifyAllObservers("Conflict with task: " + existing);
                return false;
            }
        }

        tasks.add(newTask);
        // always keep them sorted, just in case
        tasks.sort(Comparator.comparing(Task::getStart));

        notifier.notifyAllObservers("Task added: " + newTask);
        return true;
    }

    public List<Task> listTasks() {
        // return an unmodifiable list so callers don’t mess with internals
        return List.copyOf(tasks);
    }

    public boolean removeTask(String id) {
        // could also use iterator here, but removeIf is nicer
        boolean removed = tasks.removeIf(t -> t.getId().equals(id));

        if (removed) {
            notifier.notifyAllObservers("Task removed: " + id);
        } else {
            // not sure if we should notify on "not found", but might be useful
            notifier.notifyAllObservers("Tried to remove missing task: " + id);
        }

        return removed;
    }

    public void markCompleted(String id) {
        for (Task t : tasks) {
            if (t.getId().equals(id)) {
                t.markCompleted();
                notifier.notifyAllObservers("Task completed: " + id);
                return;
            }
        }
        // NOTE: calling this for missing task (could spam observers, maybe reconsider later)
        notifier.notifyAllObservers("Task not found: " + id);
    }

    // TODO: maybe add a "findById" helper instead of repeating the loop in multiple methods
}
