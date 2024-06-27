package com.example.jeucalculmental;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicManager {
    private static MediaPlayer mediaPlayer;
    private static int currentVolume = 50;
    private static int currentIndex = 0;
    private static int[] musicResources = {R.raw.musique1, R.raw.musique2, R.raw.musique3, R.raw.musique4};

    public static void startMusic(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

        mediaPlayer = MediaPlayer.create(context, musicResources[currentIndex]);
        mediaPlayer.setLooping(false);
        mediaPlayer.setVolume(currentVolume / 100f, currentVolume / 100f);

        mediaPlayer.setOnCompletionListener(mp -> {
            currentIndex = (currentIndex + 1) % musicResources.length;
            startMusic(context);
        });

        mediaPlayer.start();
    }

    public static void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public static void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public static boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    public static void setVolume(int volume) {
        currentVolume = volume;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(currentVolume / 100f, currentVolume / 100f);
        }
    }

    public static int getVolume() {
        return currentVolume;
    }

    public static void playNext(Context context) {
        currentIndex = (currentIndex + 1) % musicResources.length;
        startMusic(context);
    }
}