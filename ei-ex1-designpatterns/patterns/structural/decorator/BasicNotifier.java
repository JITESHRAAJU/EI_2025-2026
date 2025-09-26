package com.jitesh.patterns.structural.decorator;

public class BasicNotifier implements Notifier {
    @Override public void send(String message) { System.out.println("Sending: " + message); }
}