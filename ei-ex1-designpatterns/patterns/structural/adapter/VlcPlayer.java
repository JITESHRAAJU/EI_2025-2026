package com.jitesh.patterns.structural.adapter;

public class VlcPlayer implements AdvancedMediaPlayer {
    public void playVlc(String fileName) { System.out.println("Playing vlc: " + fileName); }
    public void playMp4(String fileName) {}
}