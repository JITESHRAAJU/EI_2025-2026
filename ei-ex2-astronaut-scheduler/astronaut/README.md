
#  Exercise 2 – Astronaut Scheduler



This project is a **CLI-based scheduling system** for managing an astronaut’s daily tasks.  
It demonstrates multiple design patterns, persistence, and testing.

---

##  Features

-  Add tasks with description, start time, and end time.
-  List all tasks (with completion status).
- Remove tasks by ID.
- Mark tasks as completed.
-  Save tasks to JSON file.
-  Load tasks from JSON file.
-  Notify observers when tasks are added, removed, or completed.
-  Detect conflicting tasks (time overlap prevention).

---

##  Design Patterns Used

- **Singleton** – `ScheduleManager` (one instance manages tasks).
- **Factory** – `TaskFactory` (creates tasks with UUIDs and parsed times).
- **Observer** – `Notifier` + `Observer` (broadcasts messages to observers).

---

### How to Run
git clone https://github.com/<your-username>/ei-ex2-astronaut-scheduler.git
cd ei-ex2-astronaut-scheduler
mvn clean package
2. Start CLI

java -cp target/ei-ex2-astronaut-scheduler-1.0-SNAPSHOT.jar com.jitesh.astronaut.Main
### CLI Commands
add → Add a new task (asks description, start HH:mm, end HH:mm).

list → Show all tasks.

remove → Remove task by ID.

complete → Mark task as done.

save → Save all tasks to JSON file.

load → Load tasks from JSON file.

exit → Quit the program.

### Example Usage

=== Astronaut Scheduler CLI ===
Commands: add | list | remove | complete | save | load | exit

> add
Description: Morning Exercise
Start (HH:mm): 07:00
End (HH:mm): 08:00
[NOTIFY] Task added: [id] Morning Exercise (07:00-08:00) TODO

> list
[id] Morning Exercise (07:00-08:00) TODO

> complete
Task ID: [id]
[NOTIFY] Task completed: [id]

> save
Filename: tasks.json
Saved.

> exit
Bye.
### Project Structure

src/main/java/com/jitesh/astronaut/
├── Main.java
├── cli/CLI.java
├── core/ScheduleManager.java
├── factory/TaskFactory.java
├── model/Task.java
├── observer/
│   ├── Notifier.java
│   └── Observer.java
└── persistence/JsonPersistence.java

### Learning Outcomes
Apply Singleton, Factory, and Observer in a real-world scheduler.

Use JSON persistence with Gson.

Implement and test CLI applications in Java.
