package com.jitesh.patterns;

import com.jitesh.patterns.behavioral.observer.NewsAgency;
import com.jitesh.patterns.behavioral.observer.Channel;
import com.jitesh.patterns.behavioral.strategy.*;
import com.jitesh.patterns.creational.singleton.Logger;
import com.jitesh.patterns.creational.factory.*;
import com.jitesh.patterns.structural.adapter.AudioPlayer;
import com.jitesh.patterns.structural.decorator.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Behavioral - Observer ===");
        NewsAgency agency = new NewsAgency();
        Channel ch1 = new Channel("Channel A");
        Channel ch2 = new Channel("Channel B");
        agency.addObserver(ch1);
        agency.addObserver(ch2);
        agency.setNews("SpaceX launched!");
        
        System.out.println("\n=== Behavioral - Strategy ===");
        PaymentContext ctx = new PaymentContext(new CreditCardPayment());
        ctx.pay(100);
        ctx.setStrategy(new PayPalPayment());
        ctx.pay(200);
        
        System.out.println("\n=== Creational - Singleton ===");
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        logger1.log("Logger instance working.");
        System.out.println("Same instance? " + (logger1 == logger2));
        
        System.out.println("\n=== Creational - Factory ===");
        Shape circle = ShapeFactory.createShape("CIRCLE");
        Shape rect = ShapeFactory.createShape("RECTANGLE");
        circle.draw();
        rect.draw();
        
        System.out.println("\n=== Structural - Adapter ===");
        AudioPlayer player = new AudioPlayer();
        player.play("mp3", "song.mp3");
        player.play("mp4", "movie.mp4");
        
        System.out.println("\n=== Structural - Decorator ===");
        Notifier notifier = new BasicNotifier();
        Notifier emailNotifier = new EmailNotifier(notifier);
        notifier.send("Basic message");
        emailNotifier.send("Decorated message");
    }
}