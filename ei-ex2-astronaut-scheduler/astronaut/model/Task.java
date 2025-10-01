package com.jitesh.astronaut.model;

import java.time.LocalTime;
import java.util.Objects;

public class Task {
    private final String id;            // unique id (for now just using UUID)
    private final String description;   // what the astronaut is supposed to do
    private final LocalTime start;      
    private final LocalTime end;
    private boolean completed;          // default false, obviously

    public Task(String id, String description, LocalTime start, LocalTime end) {
        // quick sanity check – not the best exception handling, but works for now
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End time can't be before start time!");
        }
        this.id = id;
        this.description = description;
        this.start = start;
        this.end = end;
        this.completed = false;   // redundant, but makes it explicit
    }

    // --- getters ---
    public String getId() { 
        return id; 
    }

    public String getDescription() { 
        return description; 
    }

    public LocalTime getStart() { 
        return start; 
    }

    public LocalTime getEnd() { 
        return end; 
    }

    public boolean isCompleted() { 
        return completed; 
    }

    // maybe later add "unmarkCompleted" if needed?
    public void markCompleted() { 
        this.completed = true; 
    }

    // NOTE: I formatted this a bit nicer for debugging purposes
    @Override
    public String toString() {
        return "[" + id + "] " 
                + description 
                + " (" + start + " → " + end + ") " 
                + (completed ? "DONE" : "TODO");
    }

    // equals & hashCode based only on id (assuming ids are unique enough)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Task)) return false;
        Task other = (Task) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // possible improvement: maybe equality should also check start/end? not sure yet
}
