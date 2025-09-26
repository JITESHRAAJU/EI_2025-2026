package com.jitesh.patterns.behavioral.observer;

public class Channel {
    private String name;
    public Channel(String name) { this.name = name; }
    public void update(String news) {
        System.out.println(name + " received: " + news);
    }
}