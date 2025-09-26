package com.jitesh.patterns.structural.decorator;

public class EmailNotifier implements Notifier {
    private Notifier wrappee;
    public EmailNotifier(Notifier wrappee) { this.wrappee = wrappee; }
    @Override public void send(String message) {
        wrappee.send(message);
        System.out.println("Also sending EMAIL: " + message);
    }
}