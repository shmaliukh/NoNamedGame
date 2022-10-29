package com.nonamed.nonamedgame;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.nonamed.nonamedgame.StaticData.*;

public final class Config {

    public static final Image HERO_ANIMATED = USIK_ANIMATED;
    public static final Image HERO_FIGHT_LEFT = USIK_FIGHT_LEFT;
    public static final Image HERO_FIGHT_RIGHT = USIK_FIGHT_RIGHT;
    public static final Image HERO_AVATAR = USIK_AVATAR;
    public static final Random RANDOM = new Random();

    public static int WINDOW_HEIGHT = 1080;
    public static int WINDOW_WIDTH = 1920;
    public static int WORLD_HEIGHT = 2560;
    public static int WORLD_WIDTH = 5120;
    public static String HERO_NAME = "VLAD";
    public static int HERO_SPEED = 5;
    public static int HERO_DAMAGE = 5;
    public static int HERO_HEALTH = 10000;
    public static int HERO_ENERGY = 10000;
    public static int HERO_ENEMY_GOAL = 7;
    public static int HERO_KEY_GOAL = 4;
    public static boolean IS_KEY_GOAL = false;
    public static int DARK_PERSON_SPEED = 3;
    public static int ENEMY_DAMAGE = 3;
    public static int VOLUME = 80;
    public static List<String> NAME_LIST = new ArrayList<>();

    static {
        NAME_LIST.add("Vlad");
        NAME_LIST.add("Yana");
        NAME_LIST.add("Vitalik");
        NAME_LIST.add("Ivan");
        NAME_LIST.add("Artem");
    }

}
