package com.jitesh.astronaut.factory;

import com.jitesh.astronaut.model.Task;
import java.time.LocalTime;
import java.util.UUID;

public class TaskFactory {

    // Honestly, I might rename this later... 
    public static Task create(String desc, String start, String end) {
        // Parsing times (assuming valid format, maybe add try/catch later if needed)
        LocalTime startAt = LocalTime.parse(start);
        LocalTime finishAt = LocalTime.parse(end);

        // Using UUID just to keep things unique, though maybe we should consider something shorter? 
        String randomId = UUID.randomUUID().toString();

        // Could have just returned directly, but this is clearer IMO
        Task t = new Task(randomId, desc, startAt, finishAt);

        // Might want to add validation here (e.g. end before start?), but leaving it for later
        return t;
    }

    // Another idea: we could add an overload that accepts LocalTime directly 
    // (just leaving a note here so I don’t forget)
    // public static Task create(String desc, LocalTime start, LocalTime end) { ... }
}
