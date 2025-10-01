package com.jitesh.astronaut.persistence;

import com.google.gson.*;
import com.jitesh.astronaut.model.Task;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.List;

public class JsonPersistence {

    // keeping a single Gson instance around (thread-safe afaik)
    private static final Gson gson = new GsonBuilder()
            // had to write custom adapters since LocalTime doesn’t serialize nicely by default
            .registerTypeAdapter(LocalTime.class, new JsonSerializer<LocalTime>() {
                @Override
                public JsonElement serialize(LocalTime time, Type typeOfSrc, JsonSerializationContext ctx) {
                    // LocalTime -> String
                    return new JsonPrimitive(time.toString());
                }
            })
            .registerTypeAdapter(LocalTime.class, new JsonDeserializer<LocalTime>() {
                @Override
                public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx)
                        throws JsonParseException {
                    // String -> LocalTime
                    return LocalTime.parse(json.getAsString());
                }
            })
            .setPrettyPrinting()  // mainly for readability, could turn off for performance later
            .create();

    // saves a list of tasks to a JSON file
    public static void save(List<Task> tasks, File file) throws IOException {
        // Using try-with-resources so we don’t forget to close the writer
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(tasks, writer);
            // NOTE: maybe add a flush() here explicitly, but writer.close() should handle it
        }
    }

    // loads tasks back from file; returns an array (could consider List<Task> instead)
    public static Task[] load(File file) throws IOException {
        if (!file.exists()) {
            // rather than failing, just return empty set for convenience
            return new Task[0];
        }
        try (Reader reader = new FileReader(file)) {
            return gson.fromJson(reader, Task[].class);
        }
    }

    // Future idea: add append/saveSingleTask methods instead of rewriting the whole list each time
    // Might make sense if tasks get large
}
