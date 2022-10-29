package com.nonamed.nonamedgame;

import javafx.scene.media.Media;
import javafx.scene.image.Image;

import java.io.File;

public class StaticData {

    public static final int HERO_HEIGHT = 128;
    public static final int HERO_WIDTH = 128;

    public static final Image USIK_ANIMATED = new Image("UsikFinal.gif");
    public static final Image USIK_FIGHT_LEFT = new Image("UsikFightLeft.gif");
    public static final Image USIK_FIGHT_RIGHT = new Image("UsikFightRight.gif");
    public static final Image USIK_AVATAR = new Image("OlgaHarlanAvatarFINAL.gif");
    public static final Image OLHA_AVATAR = new Image("OlgaHarlanAvatar (1).gif");
    public static final Image HUD = new Image("HudFinal.png");

    public static final Image SVINO_PES_ANIMATED = new Image("SvinoPes 3.gif");
    public static final Image BACKGROUND_IMAGE = new Image("backgroundGameScene.png");
    public static final Image SVINO_PES_3_IMAGE = new Image("SvinoPes 3.gif");
    public static final Image SVINO_PES_HATCH = new Image("SvinoPes Hach.gif");
    public static final Image SVINO_PES_ORK = new Image("SvinoPes Ork.gif");
    public static final Image SVINO_PES_BURYAT = new Image("SvinoPesBuryat.gif");
    public static final Image SVINO_PES = new Image("SvinoPes.gif");
    public static int SCORE = 0;

    public static final Media PlANE_SOUND = new Media(new File("src/main/resources/com/nonamed/nonamedgame/music/music.mp3").toURI().toString());
    public static final Media MAIN_MENU_SOUND = new Media(new File("src/main/resources/com/nonamed/nonamedgame/music/main-menu-music.mp3").toURI().toString());
    public static final Media GAME_OVER_SOUND = new Media(new File("src/main/resources/com/nonamed/nonamedgame/music/game-over.mp3").toURI().toString());

}
