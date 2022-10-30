package com.nonamed.nonamedgame;

import javafx.scene.media.Media;
import javafx.scene.image.Image;

import java.io.File;

public class StaticData {

    public static final Image HUD = new Image("HudFinal.png");
    public static final Image BACKGROUND_IMAGE = new Image("MapFinal.png");
    public static final Image SCORE_IMAGE = new Image("score-image.png");
    public static final Image LOGO_IMAGE = new Image("logo.png");

    public static final Image USIK_ANIMATED = new Image("UsikFinal.gif");
    public static final Image USIK_FIGHT_LEFT = new Image("UsikFightLeft.gif");
    public static final Image USIK_FIGHT_RIGHT = new Image("UsikFightRight.gif");
    public static final Image USIK_AVATAR = new Image("Usik-Avatar.gif");

    public static final Image OLHA_ANIMATED = new Image("OlgaGood.gif");
    public static final Image OLHA_FIGHT_LEFT = new Image("OlgaLeft.gif");
    public static final Image OLHA_FIGHT_RIGHT = new Image("OlgaRight.gif");
    public static final Image OLHA_AVATAR = new Image("OlgaHarlanAvatarFINAL.gif");

    public static final Image SVINO_PES_3_IMAGE = new Image("SvinoStandart.gif");
    public static final Image SVINO_PES_HATCH = new Image("SvinoPesHach.gif");
    public static final Image SVINO_PES_ORK = new Image("SvinoPesOrkAmimate.gif");
    public static final Image SVINO_PES_BURYAT = new Image("SvinoBuryatAnim.gif");
    public static final Image SVINO_PES = new Image("SvinoStandart.gif");
    public static int SCORE = 0;

    public static final Media PlANE_SOUND = new Media(new File("src/main/resources/music.mp3").toURI().toString());
    public static final Media MAIN_MENU_SOUND = new Media(new File("src/main/resources/main-menu-music.mp3").toURI().toString());
    public static final Media GAME_OVER_SOUND = new Media(new File("src/main/resources/game-over.mp3").toURI().toString());

    public static final Media ON_BUTTON_PRESS_SOUND_V1 =  new Media(new File("src/main/resources/bokserskiy-silnyiy-udar.mp3").toURI().toString());

}
