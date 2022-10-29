package com.nonamed.nonamedgame.utils;

import com.nonamed.nonamedgame.Config;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.nonamed.nonamedgame.Config.RANDOM;

public class HeroSoundService {

    public static final List<Media> musicList = new ArrayList<>();
    public static Media buzzer = new Media(new File("src/main/resources/com/nonamed/nonamedgame/music/music.mp3").toURI().toString());
    public static MediaPlayer mediaPlayer = new MediaPlayer(buzzer);

    static {
        musicList.add(new Media(new File("src/main/resources/com/nonamed/nonamedgame/hero-frazes/v-zavtrashniy-den.mp3").toURI().toString()));
    }

    public static void say() {
        if (mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            mediaPlayer = new MediaPlayer(musicList.get(RANDOM.nextInt(musicList.size())));
            mediaPlayer.setVolume(Config.VOLUME_LEVEL);
            mediaPlayer.play();
        }
    }

}
