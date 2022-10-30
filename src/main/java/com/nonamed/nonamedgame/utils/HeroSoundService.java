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
    public static final List<Media> damageMusicList = new ArrayList<>();
    public static final List<Media> eatMusicList = new ArrayList<>();
    public static final Media EAT_VARENUK = new Media(new File("src/main/resources/com/nonamed/nonamedgame/hero-frazes/eat/eat-varenik.mp3").toURI().toString());
    public static final Media EAT_APPLE = new Media(new File("src/main/resources/com/nonamed/nonamedgame/hero-frazes/eat/poedanie-ukus-yabloka.mp3").toURI().toString());
    public static final Media EAT_COIN = new Media(new File("src/main/resources/com/nonamed/nonamedgame/hero-frazes/eat/monetyi-vyisyipayut.mp3").toURI().toString());
    public static MediaPlayer heroMediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/hero-frazes/if-damaged/bokserskiy-silnyiy-udar.mp3").toURI().toString()));
    public static MediaPlayer eatMediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/hero-frazes/if-damaged/bokserskiy-silnyiy-udar.mp3").toURI().toString()));

    static {
        musicList.add(new Media(new File("src/main/resources/hero-frazes/ne-mozu-pidibratu-slova.mp3").toURI().toString()));
        musicList.add(new Media(new File("src/main/resources/hero-frazes/ne-potribni-grohi.mp3").toURI().toString()));
        musicList.add(new Media(new File("src/main/resources/hero-frazes/ne-mozu-pidibratu-slova.mp3").toURI().toString()));
        musicList.add(new Media(new File("src/main/resources/hero-frazes/v-zavtrashniy-den.mp3").toURI().toString()));
        musicList.add(new Media(new File("src/main/resources/hero-frazes/bud-prosche.mp3").toURI().toString()));
        musicList.add(new Media(new File("src/main/resources/hero-frazes/moscal-vopli/a-vot-c4a-dlya-dushi.mp3").toURI().toString()));
        musicList.add(new Media(new File("src/main/resources/hero-frazes/moscal-vopli/ai-mlya.mp3").toURI().toString()));
        musicList.add(new Media(new File("src/main/resources/hero-frazes/moscal-vopli/berem-teplogo.mp3").toURI().toString()));
        musicList.add(new Media(new File("src/main/resources/hero-frazes/moscal-vopli/bratva-kopita-otkinu.mp3").toURI().toString()));
        musicList.add(new Media(new File("src/main/resources/hero-frazes/moscal-vopli/ne-strelya.mp3").toURI().toString()));
        musicList.add(new Media(new File("src/main/resources/hero-frazes/moscal-vopli/chewbacca_swf.mp3").toURI().toString()));
        musicList.add(new Media(new File("src/main/resources/hero-frazes/moscal-vopli/o-privet.mp3").toURI().toString()));
        musicList.add(new Media(new File("src/main/resources/hero-frazes/moscal-vopli/puk.mp3").toURI().toString()));

        damageMusicList.add(new Media(new File("src/main/resources/hero-frazes/if-damaged/bokserskiy-silnyiy-udar.mp3").toURI().toString()));
        damageMusicList.add(new Media(new File("src/main/resources/hero-frazes/if-damaged/bokserskiy-udar-po-grushe.mp3").toURI().toString()));
        damageMusicList.add(new Media(new File("src/main/resources/hero-frazes/if-damaged/gluhoy-udar-po-grushe-v-bokserskih-perchatkah.mp3").toURI().toString()));
        damageMusicList.add(new Media(new File("src/main/resources/hero-frazes/if-damaged/silnyiy-udar.mp3").toURI().toString()));
        damageMusicList.add(new Media(new File("src/main/resources/hero-frazes/if-damaged/zvuk-udara.mp3").toURI().toString()));
        damageMusicList.add(new Media(new File("src/main/resources/hero-frazes/if-damaged/zvuk-udara2.mp3").toURI().toString()));



    }

    public static void say() {
        if (heroMediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            heroMediaPlayer.stop();
            MediaPlayer heroMediaPlayer = new MediaPlayer(musicList.get(RANDOM.nextInt(musicList.size())));
            heroMediaPlayer.setVolume(Config.VOLUME_LEVEL + 0.3);
            heroMediaPlayer.play();
        }
    }

    public static void onButtonPress(){
        if (heroMediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            heroMediaPlayer.stop();
            MediaPlayer heroMediaPlayer = new MediaPlayer(damageMusicList.get(RANDOM.nextInt(damageMusicList.size())));
            heroMediaPlayer.setVolume(Config.VOLUME_LEVEL + 0.3);
            heroMediaPlayer.play();
        }
    }

    public static void sayIfDamaged() {
        if (heroMediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            heroMediaPlayer.stop();
            heroMediaPlayer = new MediaPlayer(damageMusicList.get(RANDOM.nextInt(damageMusicList.size())));
            heroMediaPlayer.setVolume(Config.VOLUME_LEVEL + 0.3);
            heroMediaPlayer.play();
        }
    }

    public static void sayIfEatCoin(){
        if (heroMediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            heroMediaPlayer.stop();
            heroMediaPlayer =new MediaPlayer(EAT_COIN);
            heroMediaPlayer.setVolume(Config.VOLUME_LEVEL + 0.3);
            heroMediaPlayer.play();
        }
    }

    public static void sayIfEatVarenik(){
        if (heroMediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            heroMediaPlayer.stop();
            heroMediaPlayer =new MediaPlayer(EAT_VARENUK);
            heroMediaPlayer.setVolume(Config.VOLUME_LEVEL + 0.3);
            heroMediaPlayer.play();
        }
    }
    public static void sayIfEatApple(){
        if (heroMediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            heroMediaPlayer.stop();
            heroMediaPlayer =new MediaPlayer(EAT_APPLE);
            heroMediaPlayer.setVolume(Config.VOLUME_LEVEL + 0.3);
            heroMediaPlayer.play();
        }
    }

}
