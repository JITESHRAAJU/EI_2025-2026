package com.jitesh.patterns.structural.adapter;

public class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedPlayer;
    public MediaAdapter(String type) {
        if (type.equalsIgnoreCase("vlc")) advancedPlayer = new VlcPlayer();
        else if (type.equalsIgnoreCase("mp4")) advancedPlayer = new Mp4Player();
    }
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) advancedPlayer.playVlc(fileName);
        else if (audioType.equalsIgnoreCase("mp4")) advancedPlayer.playMp4(fileName);
    }
}