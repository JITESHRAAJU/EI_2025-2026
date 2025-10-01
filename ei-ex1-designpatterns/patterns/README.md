Exercise 1 – Design Patterns

This project demonstrates **six classic software design patterns** in Java, organized into behavioral, 
creational, and structural categories.

---

## Patterns Implemented

###  Behavioral
1. **Observer** – `NewsAgency` notifies multiple `Channel` subscribers when news updates.
2. **Strategy** – `PaymentStrategy` with `CreditCardPayment` and `PayPalPayment` as interchangeable algorithms.

### Creational
3. **Singleton** – `Logger` ensures only one instance exists throughout the application.
4. **Factory** – `ShapeFactory` creates `Circle` and `Rectangle` objects.

###  Structural
5. **Adapter** – `AudioPlayer` plays different media formats via `MediaAdapter`.
6. **Decorator** – `Notifier` wrapped with `EmailNotifier` to extend functionality.

---

##  How to Run

git clone https://github.com/<your-username>/ei-ex1-designpatterns.git
cd ei-ex1-designpatterns
mvn clean package

2. Run demo
java -cp target/ei-ex1-designpatterns-1.0-SNAPSHOT.jar com.jitesh.patterns.Main

### Example Output
=== Behavioral - Observer ===
Channel A received: SpaceX launched!
Channel B received: SpaceX launched!

=== Behavioral - Strategy ===
Paid 100 using Credit Card
Paid 200 using PayPal

=== Creational - Singleton ===
[LOG] Logger instance working.
Same instance? true

=== Creational - Factory ===
Drawing Circle
Drawing Rectangle

=== Structural - Adapter ===
Playing mp3: song.mp3
Playing mp4: movie.mp4

=== Structural - Decorator ===
Sending: Basic message
Sending: Decorated message
Also sending EMAIL: Decorated message

### Project Structure
src/main/java/com/jitesh/patterns/
├── Main.java
├── behavioral/
│   ├── observer/
│   └── strategy/
├── creational/
│   ├── singleton/
│   └── factory/
└── structural/
    ├── adapter/
    └── decorator/

### Learning Outcomes

Understand Observer, Strategy, Singleton, Factory, Adapter, and Decorator patterns.

See how design patterns make code flexible, reusable, and maintainable
