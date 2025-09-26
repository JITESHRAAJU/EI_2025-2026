package com.jitesh.patterns.creational.factory;

public class ShapeFactory {
    public static Shape createShape(String type) {
        return switch(type.toUpperCase()) {
            case "CIRCLE" -> new Circle();
            case "RECTANGLE" -> new Rectangle();
            default -> throw new IllegalArgumentException("Unknown shape: " + type);
        };
    }
}