package com.jitesh.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class NewsAgency {
    private String news;
    private final List<Channel> observers = new ArrayList<>();

    public void addObserver(Channel channel) { observers.add(channel); }

    public void setNews(String news) {
        this.news = news;
        for (Channel c : observers) c.update(news);
    }
}